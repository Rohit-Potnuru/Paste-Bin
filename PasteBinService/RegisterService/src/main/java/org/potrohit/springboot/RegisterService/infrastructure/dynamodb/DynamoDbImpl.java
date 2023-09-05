package org.potrohit.springboot.RegisterService.infrastructure.dynamodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.potrohit.springboot.RegisterService.domain.exceptions.NonRetryableException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

/**
 * This class implements the crud operations for Dynamodb.
 */
@RequiredArgsConstructor
@Log4j2
@Component
public class DynamoDbImpl {
    @Autowired
    private final DynamoDbTable<PasteBinMetaData> pasteBinMetaDataTable;

    public PasteBinMetaData getItem(final Key primaryKey) {
        try {
            PasteBinMetaData metaData = pasteBinMetaDataTable.getItem(primaryKey);
            log.info("Fetched meta data from ddb. ( metaData : {} )", metaData.toString());
            return metaData;
        } catch (final Exception ex) {
            throw new NonRetryableException(ex);
        }
    }

    public void putItem(final PasteBinMetaData metaData) {
        try {
            pasteBinMetaDataTable.putItem(metaData);
            log.info("Executed put item call in DDB. ( metaData : {} )", metaData.toString());
        } catch (final Exception ex) {
            throw new NonRetryableException(ex);
        }
    }

    public void updateItem(final PasteBinMetaData metaData) {
        try {
            pasteBinMetaDataTable.updateItem(metaData);
            log.info("Executed update item in DDB. ( metaData : {} )", metaData.toString());
        } catch (final Exception ex) {
            throw new NonRetryableException(ex);
        }
    }

    public void deleteItem(final PasteBinMetaData metaData) {
        try {
            pasteBinMetaDataTable.deleteItem(metaData);
            log.info("Executed delete item in DDB. ( metaData : {} )", metaData.toString());
        } catch (final Exception ex) {
            throw new NonRetryableException(ex);
        }
    }

    public PageIterable<PasteBinMetaData> queryTable(final QueryEnhancedRequest tableQuery) {
        try {
            return pasteBinMetaDataTable.query(tableQuery);
        } catch (final Exception ex) {
            throw new NonRetryableException(ex);
        }
    }
}