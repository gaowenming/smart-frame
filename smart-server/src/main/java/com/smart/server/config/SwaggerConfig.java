package com.smart.server.config;

import org.springframework.beans.factory.annotation.Value;
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

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean swaggerEnable ;

    @Bean
    public Docket smartApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.smart.server")).paths(PathSelectors.any()).build().enable(swaggerEnable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("RESTful APIs").description("接口描述").contact(new Contact("admin", "", "gaowm0207@163.com")).version("1.0").build();
    }

}