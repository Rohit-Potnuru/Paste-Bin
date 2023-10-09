package org.potrohit.springboot.GetPasteBinService.infrastructure.dynamodb;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.NonRetryableException;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.InternalServerException;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.PasteBinURLNotfoundExpection;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinVao;
import org.potrohit.springboot.GetPasteBinService.infrastructure.s3.S3Details;
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

    public PageIterable<PasteBinMetaData> queryTable(final QueryEnhancedRequest tableQuery) {
        try {
            return pasteBinMetaDataTable.query(tableQuery);
        } catch (final Exception ex) {
            throw new InternalServerException("DynamoDbImpl/getItem: Error occurred while getItem", ex);
        }
    }
}