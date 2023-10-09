//package org.potrohit.springboot.GetPasteBinService.injections.docs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//
//import java.util.Collections;
//
//@Configuration
//@EnableOpenApi // Switched from @EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30) // Adjusted to use OAS_30
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.potrohit.springboot")) // Adjust this to your base package
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "Get PasteBin API",
//                "This API allows Get operations for PasteBin.",
//                "v1.0",
//                "Terms of Service URL",
//                new Contact("rohit potnuru", "", "rohitpotnuru1997@gmail.com"),
//                "API License",
//                "License URL",
//                Collections.emptyList());
//    }
//}
