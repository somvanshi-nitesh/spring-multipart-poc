package com.example.multipart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("File Upload API")
                        .version("1.0.0")
                        .description("REST API for uploading and processing large files (3MB+) using Spring Boot multipart.\n\n" +
                                "**Key Features:**\n" +
                                "- Supports files up to 50MB\n" +
                                "- Multiple file upload support\n" +
                                "- File metadata extraction\n" +
                                "- Memory-efficient streaming processing\n\n" +
                                "**Production Fix:**\n" +
                                "This API demonstrates the proper configuration for handling large file uploads " +
                                "without hitting Spring Boot's default 1MB limit.\n\n" +
                                "**Configuration:**\n" +
                                "- max-file-size: 50MB\n" +
                                "- max-request-size: 100MB")
                        .contact(new Contact()
                                .name("API Support")
                                .email("athar_saqibXXX@example.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local development server")
                ));
    }
}
