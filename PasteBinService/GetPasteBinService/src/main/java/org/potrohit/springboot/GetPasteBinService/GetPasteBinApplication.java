package org.potrohit.springboot.GetPasteBinService;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetPasteBinApplication {
    private static final Logger logger = Logger.getLogger(GetPasteBinApplication.class);

    public static void main(String[] args) {
        logger.info("Get PasteBin Application Started");
        SpringApplication.run(GetPasteBinApplication.class, args);
        logger.info("Get PasteBin's Spring Boot Application Started");
    }
}


