package org.potrohit.springboot.RegisterService.domain.vao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class PasteBinMetaDataStoreVao {
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
    public String getUserId() { return this.userId; }
}
