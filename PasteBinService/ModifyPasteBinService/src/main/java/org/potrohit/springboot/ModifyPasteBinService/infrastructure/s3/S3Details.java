package org.potrohit.springboot.ModifyPasteBinService.infrastructure.s3;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class S3Details {
    String s3Bucket;
    String s3ObjectKey;
}
