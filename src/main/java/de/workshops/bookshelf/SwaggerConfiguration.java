package de.workshops.bookshelf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class SwaggerConfiguration {

    @Autowired
    MyProperties myProperties;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Bookshelf API")
                                .version("v0.0.1")
                                .contact(new Contact().name(myProperties.getName()).email(myProperties.getMail()))
                                .license(new License()
                                        .name("MIT License")
                                        .url("https://opensource.org/licenses/MIT")
                                )
                );
    }
}
