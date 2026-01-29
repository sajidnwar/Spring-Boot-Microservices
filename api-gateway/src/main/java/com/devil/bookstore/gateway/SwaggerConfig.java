package com.devil.bookstore.gateway;

import jakarta.annotation.PostConstruct;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.server.mvc.config.GatewayMvcProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    private final GatewayMvcProperties gatewayProperties;
    private final SwaggerUiConfigProperties swaggerUiConfigProperties;

    public SwaggerConfig(GatewayMvcProperties gatewayProperties, SwaggerUiConfigProperties swaggerUiConfigProperties) {
        this.gatewayProperties = gatewayProperties;
        this.swaggerUiConfigProperties = swaggerUiConfigProperties;
    }

    @PostConstruct
    public void init() {
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();

        // In Boot 4 MVC, we iterate through the GatewayMvcProperties routes
        gatewayProperties.getRoutes().stream()
                .filter(route -> route.getId().matches(".*-service"))
                .forEach(route -> {
                    String name = route.getId().replaceAll("-service", "");

                    // We use the virtual path we created in application.yml
                    AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl =
                            new AbstractSwaggerUiConfigProperties.SwaggerUrl(
                                    name, "/v3/api-docs/" + name, null);
                    urls.add(swaggerUrl);
                });

        swaggerUiConfigProperties.setUrls(urls);
    }
}
