package org.potrohit.springboot.ModifyPasteBinService.infrastructure.dynamodb;

import lombok.extern.log4j.Log4j2;

import org.potrohit.springboot.ModifyPasteBinService.domain.exceptions.NonRetryableException;
import org.potrohit.springboot.ModifyPasteBinService.domain.exceptions.common.InternalServerException;
import org.potrohit.springboot.ModifyPasteBinService.domain.exceptions.common.PasteBinURLNotfoundExpection;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;
import org.potrohit.springboot.ModifyPasteBinService.infrastructure.s3.S3Details;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

/**
 * This class implements the crud operations for Dynamodb.
 */
@Log4j2
@Component
public class DynamoDbImpl {
    @Autowired
    private final DynamoDbTable<PasteBinMetaData> pasteBinMetaDataTable = null;

    public PasteBinMetaData getPasteBinMetaDataS3(final PasteBinVao pasteBinVao, final S3Details s3Details) {
        return PasteBinMetaData.builder()
                .pasteBinId(pasteBinVao.getPasteBinId())
                .userId(pasteBinVao.getUserId())
                .itemStatus(pasteBinVao.getItemStatus())
                .expiryTime(pasteBinVao.getExpiryTime())
                .s3Bucket(s3Details.getS3Bucket())
                .s3Key(s3Details.getS3ObjectKey())
                .build();
    }

    public PasteBinMetaData getPasteBinMetaDataS3(final PasteBinVao pasteBinVao, final S3Details s3Details, Boolean isActive) {
        return PasteBinMetaData.builder()
                .pasteBinId(pasteBinVao.getPasteBinId())
                .userId(pasteBinVao.getUserId())
                .itemStatus(pasteBinVao.getItemStatus())
                .expiryTime(pasteBinVao.getExpiryTime())
                .s3Bucket(s3Details.getS3Bucket())
                .s3Key(s3Details.getS3ObjectKey())
                .isActive(isActive)
                .build();
    }

    public PasteBinMetaData getItem(final Key primaryKey) {
        try {
            log.info("DynamoDbImpl/getItem: Fetching meta data from ddb.");
            PasteBinMetaData metaData = pasteBinMetaDataTable.getItem(primaryKey);
            if(metaData == null) {
                throw new PasteBinURLNotfoundExpection("DynamoDbImpl/getItem: There is no Key present in DynamoDB");
            }
            log.info("DynamoDbImpl/getItem: Fetched meta data from ddb is complete.");
            return metaData;
        } catch (final Exception ex) {
            throw new InternalServerException("DynamoDbImpl/getItem: Error occurred while getItem", ex);
        }
    }

    public void putItem(final PasteBinMetaData metaData) {
        try {
            pasteBinMetaDataTable.putItem(metaData);
            log.info("DynamoDbImpl/putItem: Executed put item call in DDB. ( metaData : {} )", metaData.toString());
        } catch (final Exception ex) {
            throw new InternalServerException("DynamoDbImpl/putItem: Error occurred while putItem", ex);
        }
    }

    public void updateItem(final PasteBinMetaData metaData) {
        try {
            pasteBinMetaDataTable.updateItem(metaData);
            log.info("DynamoDbImpl/updateItem: Executed update item in DDB. ( metaData : {} )", metaData.toString());
        } catch (final Exception ex) {
            throw new InternalServerException("DynamoDbImpl/updateItem: Error occurred while updateItem", ex);
        }
    }

    public void deleteItem(final PasteBinMetaData metaData) {
        try {
            pasteBinMetaDataTable.deleteItem(metaData);
            log.info("DynamoDbImpl/deleteItem: Executed delete item in DDB. ( metaData : {} )", metaData.toString());
        } catch (final Exception ex) {
            throw new InternalServerException("DynamoDbImpl/deleteItem: Error occurred while deleteItem", ex);
        }
    }
}