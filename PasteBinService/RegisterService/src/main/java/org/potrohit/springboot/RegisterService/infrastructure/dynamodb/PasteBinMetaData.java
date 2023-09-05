package org.potrohit.springboot.RegisterService.infrastructure.dynamodb;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.potrohit.springboot.RegisterService.infrastructure.s3.S3Details;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class PasteBinMetaData implements Comparable<PasteBinMetaData> {
    private String pasteBinId;
    private String userId;
    private String s3Bucket;
    private String s3Key;
    private Long creationDate;
    private String itemStatus;
    private Boolean isActive;
    private Long expiryTime;

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
