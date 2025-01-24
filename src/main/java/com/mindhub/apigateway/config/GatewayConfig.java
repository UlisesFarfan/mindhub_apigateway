package com.mindhub.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocationBuilder) {
        return routeLocationBuilder.routes()
                .route("USER-MICROSERVICE", r -> r.path("/api/users/**")
                        .uri("lb://user-microservice"))
                .route("PRODUCT-MICROSERVICE", r -> r.path("/api/products/**")
                        .uri("lb://product-microservice"))
                .route("ORDERMODEL-MICROSERVICE", r -> r.path("/api/order/**")
                        .uri("lb://ordermodel-microservice"))
                .route("ORDERMODEL-MICROSERVICE", r -> r.path("/api/order_item/**")
                        .uri("lb://ordermodel-microservice"))
                .build();
    }

}
