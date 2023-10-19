package com.example.restApiMedicalInsurance.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Person Medical Insurance API")
                        .description("Rest Api example for medical information system")
                        .version("v0.0.1"))
                .components(new Components());
    }
}
