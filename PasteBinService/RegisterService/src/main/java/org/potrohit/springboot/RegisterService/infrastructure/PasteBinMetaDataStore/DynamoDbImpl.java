package org.potrohit.springboot.RegisterService.infrastructure.PasteBinMetaDataStore;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@RequiredArgsConstructor
@Log4j2
public class DynamoDbImpl {
    @Autowired
    private final DynamoDbEnhancedClient ddbClient;

    public PasteBinMetaData getItem() {
        DynamoDbTable<PasteBinMetaData> pasteBinMetaDataTable = ddbClient.table("PasteBinMetaData", TableSchema.fromBean(PasteBinMetaData.class));
        PasteBinMetaData metaData = pasteBinMetaDataTable.getItem(Key.builder()
                        .partitionValue("1")
                .build());
        log.error("skngn@ : {}", metaData.toString());
        return metaData;
    }
}