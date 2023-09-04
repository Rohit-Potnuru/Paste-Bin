package org.potrohit.springboot.RegisterService.infrastructure.PasteBinDataStore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    @Bean(destroyMethod = "close")
    public S3Client generateS3Client() {
        String accessKey = "asd";
        String secretKey = "asdfa";

        Region region = Region.US_EAST_1;
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));

        return S3Client.builder()
                .region(region)
                .credentialsProvider(staticCredentialsProvider)
                .build();
    }
}
