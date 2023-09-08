package org.potrohit.springboot.RegisterService.infrastructure.s3;

import org.potrohit.springboot.RegisterService.domain.vao.PasteBinVao;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

@Log4j2
@Component
public class S3AdapterImpl {

    @Autowired
    private final S3Client s3Client = null;

    @Autowired
    private final S3Presigner s3Presigner = null;

    private static final String S3Bucket = "paste_bin";

    public S3Details getS3Details(final PasteBinVao pasteBinVao) {
        return S3Details.builder()
                .s3Bucket(S3Bucket)
                .s3ObjectKey(pasteBinVao.getPasteBinId())
                .build();
    }

    public String getPreSignedDownloadURL(S3Details s3Data) {
        String s3Bucket = s3Data.getS3Bucket();
        String s3ObjectKey = s3Data.getS3ObjectKey();

        try {
            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .bucket(s3Bucket)
                    .key(s3ObjectKey)
                    .build();
            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .getObjectRequest(objectRequest)
                    .build();

            PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
            String presignedDownloadURL = presignedRequest.url().toString();

            System.out.println("Presigned URL to download a file to: " +presignedDownloadURL);
            System.out.println("Which HTTP method needs to be used when uploading a file: " + presignedRequest.httpRequest().method());
            return presignedDownloadURL;
        }
        catch (S3Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public String getPreSignedUploadURL(S3Details s3Data, String contentType) {
        String s3Bucket = s3Data.getS3Bucket();
        String s3ObjectKey = s3Data.getS3ObjectKey();

        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(s3Bucket)
                    .key(s3ObjectKey)
                    .contentType(contentType)
                    .build();
            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .putObjectRequest(objectRequest)
                    .build();

            PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
            String presignedUploadURL = presignedRequest.url().toString();

            System.out.println("Presigned URL to upload a file to: " +presignedUploadURL);
            System.out.println("Which HTTP method needs to be used when uploading a file: " + presignedRequest.httpRequest().method());
            return presignedUploadURL;
        }
        catch (S3Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public void deleteBucketObject(S3Details s3Data) {
        String s3Bucket = s3Data.getS3Bucket();
        String s3ObjectKey = s3Data.getS3ObjectKey();

        ArrayList<ObjectIdentifier> toDelete = new ArrayList<>();
        toDelete.add(ObjectIdentifier.builder()
                .key(s3ObjectKey)
                .build());

        try {
            DeleteObjectsRequest dor = DeleteObjectsRequest.builder()
                    .bucket(s3Bucket)
                    .delete(Delete.builder()
                            .objects(toDelete).build())
                    .build();

            s3Client.deleteObjects(dor);

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

        System.out.println("Done!");
    }

    public void getURL(S3Details s3Data) {
        String s3Bucket = s3Data.getS3Bucket();
        String s3ObjectKey = s3Data.getS3ObjectKey();

        try {
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(s3Bucket)
                    .key(s3ObjectKey)
                    .build();

            URL url = s3Client.utilities().getUrl(request);
            System.out.println("The URL for  "+s3ObjectKey +" is "+ url);

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}


