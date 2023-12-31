package org.potrohit.springboot.RegisterService.injections.infrastructure;

import org.potrohit.springboot.RegisterService.infrastructure.dynamodb.PasteBinMetaData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfig {
    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(
                        DynamoDbClient.builder()
                                .region(Region.US_EAST_1)
                                .credentialsProvider(ProfileCredentialsProvider.create())
                                .httpClient(UrlConnectionHttpClient.builder().build())
                                .build())
                .build();
    }

    @Bean
    public DynamoDbTable<PasteBinMetaData> getPasteBinMetaDataTable(final DynamoDbEnhancedClient client) {
        return client.table("PasteBinMetaData",
                TableSchema.fromBean(PasteBinMetaData.class));
    }
}
