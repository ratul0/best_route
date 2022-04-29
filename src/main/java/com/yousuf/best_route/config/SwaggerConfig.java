package com.yousuf.best_route.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Finding Best Route API")
                        .description("Find the best route between two ports")
                        .version("v1.0.0")
                        .contact(new Contact().name("Yousuf Khan").email("ratulcse27@gmailcom"))
                        .license(new License().name("Apache 2.0")));
    }
}
