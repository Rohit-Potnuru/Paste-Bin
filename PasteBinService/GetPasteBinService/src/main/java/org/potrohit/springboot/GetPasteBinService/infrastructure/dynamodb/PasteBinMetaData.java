package org.potrohit.springboot.GetPasteBinService.infrastructure.dynamodb;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@ToString
@EqualsAndHashCode
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasteBinMetaData implements Comparable<PasteBinMetaData> {
    private String pasteBinId;
    private String userId;
    private String itemStatus;
    private Long expiryTime;
    private String s3Bucket;
    private String s3Key;
    private Boolean isActive;
    private Long creationDate;

    @DynamoDbPartitionKey
    public String getPasteBinId() { return this.pasteBinId; }

    @DynamoDbSortKey
    @DynamoDbSecondaryPartitionKey(indexNames = "PasteBinIdsByUserId")
    public String getUserId() { return this.userId; }

    @Override
    public int compareTo(PasteBinMetaData metaData) {
        return this.pasteBinId.compareTo(metaData.pasteBinId);
    }
}
