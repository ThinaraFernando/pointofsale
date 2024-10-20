package com.ijse.pointofsale.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Point of Sale API")
                        .description("API documentation for the Point of Sale System")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth")) // Updated scheme name to match
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", // Name this exactly as referenced in `addSecurityItem`
                                new SecurityScheme()
                                        .name("BearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))); // Indicate it's JWT format
    }


}
