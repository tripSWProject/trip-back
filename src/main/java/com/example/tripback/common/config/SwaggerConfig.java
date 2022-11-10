package com.example.tripback.common.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("tripback")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public OpenAPI whaiOpenAPI() {
        return new OpenAPI().info(info())
                .components(components());
    }

    private Info info() {
        return new Info()
                .title("Trip Project API")
                .version("1.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }

    private Components components() {
        return new Components()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer").bearerFormat("JWT"));
    }
}