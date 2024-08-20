package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Usage Ingestion API")
                                .description("API for ingesting and managing usage data.")
                                .contact(
                                        new Contact()
                                                .name("API Support")
                                                .email("support@example.com")
                                )
                                .version("1.0.0")
                )
                .components(
                        new Components()
                                .addSecuritySchemes("FullAccessApiKey", new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.QUERY)
                                        .name("API Key")
                                )
                )
        ;
    }
}