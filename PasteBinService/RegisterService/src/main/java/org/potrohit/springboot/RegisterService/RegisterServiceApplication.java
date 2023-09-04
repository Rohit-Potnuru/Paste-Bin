package org.potrohit.springboot.RegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.net.URL;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegisterServiceApplication {
    @Autowired
    private static S3Client s3;
    public static void main(String[] args) {
        SpringApplication.run(RegisterServiceApplication.class, args);

        //---------------------------------------------
        String[] arguments = {"bucketName", "keyName"};
        final String usage = "\n" +
                "Usage:\n" +
                "    <bucketName> <keyName> \n\n" +
                "Where:\n" +
                "    bucketName - The Amazon S3 bucket name.\n\n"+
                "    keyName - A key name that represents the object. \n\n";

        if (arguments.length != 2) {
            System.out.println(usage);
            System.exit(1);
        }

        String bucketName = arguments[0];
        String keyName = arguments[1];

        getURL(bucketName,keyName);

    }
    public static void getURL(String bucketName, String keyName ) {

        try {
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .build();

            URL url = s3.utilities().getUrl(request);
            System.out.println("The URL for  "+keyName +" is "+ url);
            s3.close();

        } catch (S3Exception e) {
            s3.close();
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}