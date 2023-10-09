package org.potrohit.springboot.ModifyPasteBinService.infrastructure.PasteBinDataStore;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.ModifyPasteBinService.domain.exceptions.NonRetryableException;
import org.potrohit.springboot.ModifyPasteBinService.domain.exceptions.common.BadRequestException;
import org.potrohit.springboot.ModifyPasteBinService.domain.exceptions.common.InternalServerException;
import org.springframework.stereotype.Component;

import org.potrohit.springboot.ModifyPasteBinService.domain.ports.PasteBinDataStore;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.dynamodb.DynamoDbImpl;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.dynamodb.PasteBinMetaData;

import org.potrohit.springboot.ModifyPasteBinService.infrastructure.s3.S3AdapterImpl;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.s3.S3Details;

@Log4j2
@Component
public class PasteBinDataStoreAdapterImpl implements PasteBinDataStore {
    private final DynamoDbImpl dynamoDb;
    private final S3AdapterImpl s3AdapterImpl;
    public PasteBinDataStoreAdapterImpl(DynamoDbImpl dynamoDb, S3AdapterImpl s3AdapterImpl) {
        this.dynamoDb = dynamoDb;
        this.s3AdapterImpl = s3AdapterImpl;
    }

    @Override
    public String registerPasteBin(final PasteBinVao pasteBinVao) {
        try {
            // Call s3Impl and get S3 details
            final S3Details s3Details = s3AdapterImpl.getS3Details(pasteBinVao);

            // Create PasteBinMetaData object
            final PasteBinMetaData pasteBinMetaData = dynamoDb.getPasteBinMetaDataS3(pasteBinVao, s3Details, true);

            // Put the PasteBinMetaData into DynamoDB
            dynamoDb.putItem(pasteBinMetaData);

            // Get pre-signed S3 URL for uploading
            String preSignedS3URL = s3AdapterImpl.getPreSignedUploadURL(s3Details, pasteBinVao.getContentType());
            log.info("PasteBinDataStoreAdapter/registerPasteBin: Pre-signed S3 URL for PasteBinId {}: {}", pasteBinVao.getPasteBinId(), preSignedS3URL);

            return preSignedS3URL;
        } catch (Exception e) {
            // You can choose to re-throw the exception or handle it as needed
            throw new InternalServerException("PasteBinDataStoreAdapter/registerPasteBin: An error occurred while registering PasteBin for pasteBinId " + pasteBinVao.getPasteBinId(), e);
        }
    }

    @Override
    public String confirmRegisterPasteBin(PasteBinVao pasteBinVao) {
        try {
            // Call s3Impl and get S3 details
            final S3Details s3Details = s3AdapterImpl.getS3Details(pasteBinVao);

            // Create PasteBinMetaData object
            final PasteBinMetaData pasteBinMetaData = dynamoDb.getPasteBinMetaDataS3(pasteBinVao, s3Details, true);

            //Check if object is present in dynamoDB
            Key key = Key.builder()
                    .partitionValue(pasteBinMetaData.getPasteBinId())
                    .build();

            PasteBinMetaData result = dynamoDb.getItem(key);
            if(!result.getUserId().equals(pasteBinVao.getUserId())) {
                throw new BadRequestException("PasteBinDataStoreAdapter/confirmRegisterPasteBin: same user is not confirming for pasteBinId " + pasteBinVao.getPasteBinId() + " UserId: " + pasteBinVao.getUserId() + " requested confirm pasteBin but, UsedId: " + result.getUserId() + " created the register request for pasteBin", "Error: " + result.getUserId() + " userId did not register the pasteBin");
            }
            if(!result.getItemStatus().equals("PENDING_REGISTER_UPLOAD")) {
                if(result.getItemStatus().equals("PENDING_UPDATE_UPLOAD")) {
                    throw new BadRequestException("PasteBinDataStoreAdapter/confirmUpdatePasteBin: Wrong api triggered by  UserId: " + pasteBinVao.getUserId() + " for pasteBinId " + pasteBinVao.getPasteBinId() + " instead upload/confirm needs to be triggered", "Error: wrong request, confirm update pasteBin instead confirming register pasteBin");
                }
                else if(result.getItemStatus().equals("UPLOADED")) {
                    throw new BadRequestException("PasteBinDataStoreAdapter/confirmUpdatePasteBin: File already uploaded for pasteBinId " + pasteBinVao.getPasteBinId(), "Error: pasteBinId: " + pasteBinVao.getPasteBinId() + " is already uploaded" );
                }
                else {
                    throw new InternalServerException("PasteBinDataStoreAdapter/confirmUpdatePasteBin: same user is not confirming for pasteBinId " + pasteBinVao.getPasteBinId() + " UserId: " + pasteBinVao.getUserId() + " requested confirm pasteBin but, UsedId: " + result.getUserId() + " created the update request for pasteBin");
                }
            }

            //Check if Object is present in S3
            s3AdapterImpl.doesObjectExists(s3Details);

            //Confirming the register status
            result.setItemStatus(pasteBinVao.getItemStatus());
            dynamoDb.putItem(result);

            // Get pre-signed S3 URL for uploading
            String preSignedS3URL = s3AdapterImpl.getPreSignedDownloadURL(s3Details);
            log.info("PasteBinDataStoreAdapter/confirmRegisterPasteBin: Pre-signed Download S3 URL for PasteBinId {}: {}", pasteBinVao.getPasteBinId(), preSignedS3URL);

            return preSignedS3URL;
        } catch (Exception e) {
            // You can choose to re-throw the exception or handle it as needed
            throw new InternalServerException("PasteBinDataStoreAdapter/confirmRegisterPasteBin: An error occurred while confirming register PasteBin for pasteBinId " + pasteBinVao.getPasteBinId(), e);
        }
    }

    @Override
    public String updatePasteBin(PasteBinVao pasteBinVao) {
        // Call s3Impl and get S3 details
        final S3Details s3Details = s3AdapterImpl.getS3Details(pasteBinVao);

        // Create PasteBinMetaData object
        final PasteBinMetaData pasteBinMetaData = dynamoDb.getPasteBinMetaDataS3(pasteBinVao, s3Details, true);

        //Check if object is present in dynamoDB
        Key key = Key.builder()
                .partitionValue(pasteBinMetaData.getPasteBinId())
                .build();
        PasteBinMetaData result = dynamoDb.getItem(key);
        if(!result.getUserId().equals(pasteBinVao.getUserId())) {
            throw new BadRequestException("PasteBinDataStoreAdapter/uploadPasteBin: same user is not updating for pasteBinId " + pasteBinVao.getPasteBinId() + " UserId: " + pasteBinVao.getUserId() + " requested confirm pasteBin but, UsedId: " + result.getUserId() + " created the register request for pasteBin", "Error: " + result.getUserId() + " userId did not register the pasteBin");
        }
        if(!result.getItemStatus().equals("UPLOADED") && !result.getItemStatus().equals("PENDING_UPDATE_UPLOAD")) {
            if(result.getItemStatus().equals("PENDING_REGISTER_UPLOAD")) {
                throw new BadRequestException("PasteBinDataStoreAdapter/uploadPasteBin: Wrong api triggered by  UserId: " + pasteBinVao.getUserId() + " for pasteBinId " + pasteBinVao.getPasteBinId() + " instead, register/confirm needs to be triggered", "Error: wrong request, confirm register pasteBin instead confirming update pasteBin" );
            }
            else {
                throw new InternalServerException("PasteBinDataStoreAdapter/uploadPasteBin: same user is not confirming for pasteBinId " + pasteBinVao.getPasteBinId() + " UserId: " + pasteBinVao.getUserId() + " requested confirm pasteBin but, UsedId: " + result.getUserId() + " created the update request for pasteBin");
            }
        }

        // Put the PasteBinMetaData into DynamoDB
        dynamoDb.putItem(pasteBinMetaData);

        // Get pre-signed S3 URL for uploading
        String preSignedS3URL = s3AdapterImpl.getPreSignedUploadURL(s3Details, pasteBinVao.getContentType());
        log.info("PasteBinDataStoreAdapter/updatePasteBin: Pre-signed S3 URL for PasteBinId {}: {}", pasteBinVao.getPasteBinId(), preSignedS3URL);

        return preSignedS3URL;
    }

    @Override
    public String confirmUpdatePasteBin(PasteBinVao pasteBinVao) {
        try {
            // Call s3Impl and get S3 details
            final S3Details s3Details = s3AdapterImpl.getS3Details(pasteBinVao);

            // Create PasteBinMetaData object
            final PasteBinMetaData pasteBinMetaData = dynamoDb.getPasteBinMetaDataS3(pasteBinVao, s3Details, true);

            //Check if object is present in dynamoDB
            Key key = Key.builder()
                    .partitionValue(pasteBinMetaData.getPasteBinId())
                    .build();

            PasteBinMetaData result = dynamoDb.getItem(key);
            if(!result.getUserId().equals(pasteBinVao.getUserId())) {
                throw new NonRetryableException("PasteBinDataStoreAdapter/confirmUpdatePasteBin: same user is not confirming for pasteBinId " + pasteBinVao.getPasteBinId() + " UserId: " + pasteBinVao.getUserId() + " requested confirm pasteBin but, UsedId: " + result.getUserId() + " created the update request for pasteBin");
            }
            if(!result.getItemStatus().equals("PENDING_UPDATE_UPLOAD")) {
                if(result.getItemStatus().equals("UPLOADED")) {
                    throw new NonRetryableException("PasteBinDataStoreAdapter/confirmUpdatePasteBin: same user is not confirming for pasteBinId " + pasteBinVao.getPasteBinId() + " UserId: " + pasteBinVao.getUserId() + " requested confirm pasteBin but, UsedId: " + result.getUserId() + " created the update request for pasteBin");
                }
                else {
                    throw new InternalServerException("PasteBinDataStoreAdapter/confirmUpdatePasteBin: same user is not confirming for pasteBinId " + pasteBinVao.getPasteBinId() + " UserId: " + pasteBinVao.getUserId() + " requested confirm pasteBin but, UsedId: " + result.getUserId() + " created the update request for pasteBin");
                }
            }

            //Check if Object is present in S3
            s3AdapterImpl.doesObjectExists(s3Details);

            //Confirming the register status
            result.setItemStatus(pasteBinVao.getItemStatus());
            dynamoDb.putItem(result);

            // Get pre-signed S3 URL for uploading
            String preSignedS3URL = s3AdapterImpl.getPreSignedDownloadURL(s3Details);
            log.info("PasteBinDataStoreAdapter/confirmUpdatePasteBin: Pre-signed Download S3 URL for PasteBinId {}: {}", pasteBinVao.getPasteBinId(), preSignedS3URL);

            return preSignedS3URL;
        } catch (Exception e) {
            // Log the error message
            log.error("PasteBinDataStoreAdapter/confirmUpdatePasteBin: Error confirming update PasteBin for pasteBinId {}: {}", pasteBinVao.getPasteBinId(), e.getMessage());

            // You can choose to re-throw the exception or handle it as needed
            throw new InternalServerException("PasteBinDataStoreAdapter/confirmUpdatePasteBin: An error occurred while confirming update PasteBin for pasteBinId " + pasteBinVao.getPasteBinId(), e);
        }
    }

    @Override
    public void deletePasteBin(final PasteBinVao pasteBinVao) {
        try {
            // Call s3Impl and get S3 details
            final S3Details s3Details = s3AdapterImpl.getS3Details(pasteBinVao);

            // Create PasteBinMetaData object
            final PasteBinMetaData pasteBinMetaData = dynamoDb.getPasteBinMetaDataS3(pasteBinVao, s3Details, true);

            //Deleting the PasteBinMetaData from DynamoDB
            dynamoDb.deleteItem(pasteBinMetaData);

            //Deleting the PasteBinMetaData from S3
            s3AdapterImpl.deleteBucketObject(s3Details);

        } catch (Exception e) {
            // You can choose to re-throw the exception or handle it as needed
            throw new InternalServerException("An error occurred while registering PasteBin for pasteBinId " + pasteBinVao.getPasteBinId(), e);
        }
    }
}
