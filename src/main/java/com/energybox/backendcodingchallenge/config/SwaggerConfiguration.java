package com.energybox.backendcodingchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(Boolean.FALSE)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.energybox.backendcodingchallenge.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Sravan Uppala's Energybox Coding Assessment API")
                .description("Backend service to manage Sensors and Gateways")
                .contact("suppala0110@gmail.com")
                .version("1.0")
                .build();
    }
}
