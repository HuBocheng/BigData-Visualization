package org.doraemon.visualize.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Jasmine's Doc",
                version = "1.0",
                description = "接口方法",
                contact = @Contact(name = "Jasmine")
        )
)
public class SwaggerConfig {
}
