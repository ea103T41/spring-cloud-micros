package com.euphy.learn;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {

    @Bean
    public OpenAPI baseOpenAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Books API").version("1.0.0").description("API for books"));
    }
}
