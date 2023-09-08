package org.potrohit.springboot.RegisterService.infrastructure.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Component
public class S3Validation {
    @Autowired
    private final S3Client s3Client = null;

    public void validateS3Client() {
        try {
            ListBucketsResponse listBucketsResponse = s3Client.listBuckets();
            System.out.println("List of S3 buckets: " + listBucketsResponse.buckets());

            // You can add more validation checks here

        } catch (S3Exception e) {
            System.err.println("Error while validating S3 client: " + e.awsErrorDetails().errorMessage());
        }
    }
}