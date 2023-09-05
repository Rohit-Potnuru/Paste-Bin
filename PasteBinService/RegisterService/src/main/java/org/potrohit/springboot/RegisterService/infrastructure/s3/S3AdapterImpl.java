package org.potrohit.springboot.RegisterService.infrastructure.s3;

import org.potrohit.springboot.RegisterService.domain.vao.PasteBinVao;

public class S3AdapterImpl {
    public S3Details getS3Details(final PasteBinVao pasteBinVao) {
        return S3Details.builder()
                .s3Bucket("BUCKET")
                .build();
    }
}
