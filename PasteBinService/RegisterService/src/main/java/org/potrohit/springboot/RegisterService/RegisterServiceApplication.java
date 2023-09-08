package org.potrohit.springboot.RegisterService;

import org.potrohit.springboot.RegisterService.infrastructure.s3.S3Validation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RegisterServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RegisterServiceApplication.class, args);

//        //For testing S3Client works
//        S3Validation s3Validation = context.getBean(S3Validation.class);
//        s3Validation.validateS3Client();
    }
}