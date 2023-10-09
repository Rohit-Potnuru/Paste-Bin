package org.potrohit.springboot.GetPasteBinService.infrastructure.pasteBinDataStore;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.BadRequestException;
import org.potrohit.springboot.GetPasteBinService.domain.ports.PasteBinDataStore;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinAccessVao;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinVao;
import org.potrohit.springboot.GetPasteBinService.infrastructure.dynamodb.DynamoDbImpl;
import org.potrohit.springboot.GetPasteBinService.infrastructure.dynamodb.PasteBinMetaData;
import org.potrohit.springboot.GetPasteBinService.infrastructure.s3.S3AdapterImpl;
import org.potrohit.springboot.GetPasteBinService.infrastructure.s3.S3Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.List;

@Log4j2
@Component
public class PasteBinDataStoreAdapterImpl implements PasteBinDataStore {

    private final DynamoDbImpl dynamoDb;
    private final S3AdapterImpl s3AdapterImpl;

    @Autowired
    public PasteBinDataStoreAdapterImpl(DynamoDbImpl dynamoDb, S3AdapterImpl s3AdapterImpl) {
        this.dynamoDb = dynamoDb;
        this.s3AdapterImpl = s3AdapterImpl;
    }

    @Override
    public PasteBinAccessVao getPasteBin(PasteBinVao pasteBinVao) {
        log.info("PasteBinDataStoreAdapterImpl/getPasteBin: Processing getPasteBin for pasteBinId: " + pasteBinVao.getPasteBinId());
        //Getting S3Details
        S3Details s3Details = s3AdapterImpl.getS3Details(pasteBinVao);

        //Constructing pasteBinMetaData from s3Details, pasteBinVao
        PasteBinMetaData pasteBinMetaData = dynamoDb.getPasteBinMetaDataS3(pasteBinVao, s3Details);

        // Retrieving pasteBinId from DynamoDb table
        log.info("PasteBinDataStoreAdapterImpl/getPasteBin: Retrieving pasteBinId from Metadata table");
        Key key = Key.builder()
                .partitionValue(pasteBinMetaData.getPasteBinId())
                .build();
        PasteBinMetaData result = dynamoDb.getItem(key);
        if(!result.getItemStatus().equals("UPLOADED")) {
            if(result.getItemStatus().equals("PENDING_REGISTER_UPLOAD")) {
                throw new BadRequestException("PasteBinDataStoreAdapterImpl/getPasteBin: Error occurred after retrieving metaData, file is not uploaded during registration", "REGISTER");
            }
            else if(result.getItemStatus().equals("PENDING_UPDATE_UPLOAD")) {
                throw new BadRequestException("PasteBinDataStoreAdapterImpl/getPasteBin: Error occurred after retrieving metaData, file is not uploaded during registration", "UPDATE");
            }
            else {
                throw new BadRequestException("PasteBinDataStoreAdapterImpl/getPasteBin: Error occurred after retrieving metaData, ItemStatus does not contain UPLOADED String");
            }
        }
        log.info("PasteBinDataStoreAdapterImpl/getPasteBin: Retrieved pasteBinId from Metadata table");

        //Checking if file exists in object Store
        log.info("PasteBinDataStoreAdapterImpl/getPasteBin: Checking is Object Exists in data Store");
        s3AdapterImpl.doesObjectExists(s3Details);

        //Generating accessURL for client to download
        log.info("PasteBinDataStoreAdapterImpl/getPasteBin: Generating accessURL for client to download");
        String accessURL = s3AdapterImpl.getPreSignedDownloadURL(s3Details);
        PasteBinAccessVao pasteBinAccessVao = PasteBinAccessVao.builder()
                .accessURL(accessURL)
                .pasteBinId(pasteBinVao.getPasteBinId())
                .build();
        log.info("PasteBinDataStoreAdapterImpl/getPasteBin: Generated accessURL for client to download for pasteBinId: " + pasteBinVao.getPasteBinId());

        return pasteBinAccessVao;
//        try {
//
//        } catch (Exception e) {
//            throw new InternalServerException("PasteBinDataStoreAdapterImpl/getPasteBin: An error occurred while processing getPasteBin", e);
//        }
    }

    @Override
    public List<PasteBinAccessVao> getUserPasteBin(PasteBinVao PasteBinVao) {
        return null;
    }
}
