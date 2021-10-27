package com.elderpereira.customerservice.config;


import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CustomerManagement API")
                        .description("Simple application for managing customers, it allows the following operations: customer search (id, email, cpf, phone), paged customer search, customer creation, customer modification and customer removal.")
                        .version("v1")
                        .contact(new Contact().name("Elder Pereira").email("elder.computacao@gmail.com")));
    }
}
