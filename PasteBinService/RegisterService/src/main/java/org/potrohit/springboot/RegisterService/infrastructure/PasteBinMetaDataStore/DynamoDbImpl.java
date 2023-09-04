package org.potrohit.springboot.RegisterService.infrastructure.PasteBinMetaDataStore;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@RequiredArgsConstructor
public class DynamoDbImpl {
    @Autowired
    private final DynamoDbEnhancedClient ddbClient;

    public
}
