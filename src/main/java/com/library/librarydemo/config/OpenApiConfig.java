package com.library.librarydemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Reactive Library API",
        version = "v1",
        description = "This documentation app provides reactive REST APIs for library",
        contact = @Contact(
            email = "gyorgy.busak@accenture.com"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:9090",
            description = "Reactive Library API (Local)"
        )
    }
)
@Configuration
public class OpenApiConfig {

    // Type in the url in browser
    // http://localhost:9090/webjars/swagger-ui/index.html?url=http://localhost:9090/v3/api-docs

}