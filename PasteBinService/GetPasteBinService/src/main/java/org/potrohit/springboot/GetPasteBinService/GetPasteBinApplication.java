package org.potrohit.springboot.GetPasteBinService;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Log4j2
@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class GetPasteBinApplication {
    public static void main(String[] args) {
        log.info("Get PasteBin Application Started");
        SpringApplication.run(GetPasteBinApplication.class, args);
        log.info("Get PasteBin's Spring Boot Application Started");
    }
}


