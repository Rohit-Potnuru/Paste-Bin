package org.potrohit.springboot.ModifyPasteBinService;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Log4j2
@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class ModifyPasteBinServiceApplication {
    public static void main(String[] args) {
        log.info("Modify PasteBin Application Started");
        SpringApplication.run(ModifyPasteBinServiceApplication.class, args);
        log.info("Modify PasteBin's Spring Boot Application Started");
    }
}