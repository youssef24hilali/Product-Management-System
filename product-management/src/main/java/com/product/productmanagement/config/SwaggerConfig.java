package com.product.productmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.product.productmanagement"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo(){
        return new ApiInfoBuilder()
                .title("Product Management System")
                .description("PMS using Spring boot & Vue js")
                .contact(new Contact("Yousse Elhilali", "https://github.com/youssef24hilali", "y.elhilali24@gmail.com"))
                .build();
    }
}
