package com.tdp.popcorn_palace.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Popcorn Palace API")
                        .version("1.0.0")
                        .description("API documentation for the Popcorn Palace movie booking system"));
    }
}
