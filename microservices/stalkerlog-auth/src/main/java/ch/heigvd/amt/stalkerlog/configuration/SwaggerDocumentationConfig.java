package ch.heigvd.amt.stalkerlog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerDocumentationConfig {
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Stalkerlog Auth API")
            .description("API to register users and get a JWT token.")
            .license("MIT")
            .licenseUrl("http://opensource.org/licenses/MIT")
            .version("1.0.0")
            .contact(new Contact("Stalkerlog team", "", "luc.wachter@heig-vd.ch"))
            .build();
    }

    @Bean
    public Docket customApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", "header")))
            .securityContexts(Collections.singletonList(
                SecurityContext.builder()
                    .securityReferences(
                        Collections.singletonList(SecurityReference.builder()
                            .reference("JWT")
                            .scopes(new AuthorizationScope[0])
                            .build()
                        )
                    )
                    .build())
            )
            .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
            .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("ch.heigvd.amt.stalkerlog.api"))
            .build();
    }
}
