package org.potrohit.springboot.GetPasteBinService.infrastructure.s3;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.NonRetryableException;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.InternalServerException;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.PasteBinURLNotfoundExpection;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.net.URL;
import java.time.Duration;

@Log4j2
@Component
public class S3AdapterImpl {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private static final String S3Bucket = "paste-bin-data";

    @Autowired
    public S3AdapterImpl(S3Client s3Client, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
        this.doesS3BucketExists();
    }

    public void doesS3BucketExists() {
        //validating S3bucket exists
        try {
            // Send a headBucket request to check if the bucket exists
            s3Client.headBucket(HeadBucketRequest.builder().bucket(S3Bucket).build());
            log.info("S3AdapterImpl-constructor: S3Bucket: {} Exists", S3Bucket);
        } catch (S3Exception e) {
            // The bucket does not exist, handle the exception here
            if ("NoSuchBucket".equals(e.awsErrorDetails().errorCode())) {
                throw new InternalServerException("S3AdapterImpl-constructor: S3Bucket: " + S3Bucket + " does not exist");
            } else {
                // Handle other S3 exceptions
                throw new InternalServerException("S3AdapterImpl-constructor: Error occurred while calling S3 ", e);
            }
        } catch (Exception e) {
            // Handle other exceptions
            throw new InternalServerException("S3AdapterImpl-constructor: Error occurred while calling doesS3BucketExists class ", e);
        } finally {
            s3Client.close();
        }
    }

    public S3Details getS3Details(final PasteBinVao pasteBinVao) {
        try {
            if (pasteBinVao != null) {
                return S3Details.builder()
                        .s3Bucket(S3Bucket)
                        .s3ObjectKey(pasteBinVao.getPasteBinId())
                        .build();
            } else {
                // Handle the case where pasteBinVao is null.
                throw new InternalServerException("S3AdapterImpl/getS3Details: pasteBinVao is null");
            }
        }  catch (Exception e) {
            throw new InternalServerException("S3AdapterImpl/getS3Details: An error occurred while constructing S3Details", e);
        }
    }

    public void doesObjectExists(final S3Details s3Data) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(s3Data.getS3Bucket())
                    .key(s3Data.getS3ObjectKey())
                    .build();
            s3Client.headObject(headObjectRequest);
        } catch (NoSuchKeyException e) {
            throw new PasteBinURLNotfoundExpection("S3AdapterImpl/doesObjectExits: File not found in S3");
        } catch(NullPointerException e) {
            // Handle the case where pasteBinVao is null.
            throw new InternalServerException("S3AdapterImpl/doesObjectExits: S3 data is null");
        }
        catch (Exception e) {
            throw new InternalServerException("S3AdapterImpl/doesObjectExits: Error occured while checking object exists in S3", e);
        }
    }

    public String getPreSignedDownloadURL(S3Details s3Data) {
        String s3Bucket = s3Data.getS3Bucket();
        String s3ObjectKey = s3Data.getS3ObjectKey();

        try {
            log.info("S3AdapterImpl/getPreSignedDownloadURL: Generating PreSignedDownloadURL by calling S3");
            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .bucket(s3Bucket)
                    .key(s3ObjectKey)
                    .build();
            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .getObjectRequest(objectRequest)
                    .build();

            PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
            if(presignedRequest == null) {
                throw new InternalServerException("S3AdapterImpl/getPreSignedDownloadURL: presignedRequest is null, Error occuring while calling S3");
            }
            log.info("S3AdapterImpl/getPreSignedDownloadURL: Generated PreSignedDownloadURL after calling S3 ");

            String presignedDownloadURL = presignedRequest.url().toString();
//            log.info("Presigned URL to download a file to: {}", presignedDownloadURL);
//            log.info("Which HTTP method needs to be used when uploading a file: {}", presignedRequest.httpRequest().method());
            return presignedDownloadURL;
        }
        catch (S3Exception e) {
            throw new InternalServerException("An error occurred while getting pre-signed download URL", e);
        }
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

            // Log the URL instead of using System.out.println
            log.info("The URL for {} is {}", s3ObjectKey, url);

        } catch (S3Exception e) {
            // Log the error message
            log.error("Error getting URL for {}/{}. Error: {}", s3Bucket, s3ObjectKey, e.getMessage());
            // You can choose to re-throw the exception or handle it as needed
            throw new RuntimeException("Error getting URL: " + e.getMessage(), e);
        }
    }
}


