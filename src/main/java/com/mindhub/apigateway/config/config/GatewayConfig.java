package com.mindhub.apigateway.config.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator customRouter(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route("USER-MICROSERVICE", r -> r.path("/api/users/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://user-microservice"))
                .route("USER-MICROSERVICE", r -> r.path("/api/auth/**")
                        .uri("lb://user-microservice"))
                .route("PRODUCT-MICROSERVICE", r -> r.path("/api/products/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://product-microservice"))
                .route("ORDERMODEL-MICROSERVICE", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://ordermodel-microservice"))
                .route("ORDERMODEL-MICROSERVICE", r -> r.path("/api/order_item/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://ordermodel-microservice"))
                .route("PRODUCER-RABBITMQ", r -> r.path("/api/producer_rabbit/**")
                        .uri("lb://producer-rabbitmq"))
                .route("EMAIL-SERVICE", r -> r.path("/api/email/**")
                        .uri("lb://email-service"))
                .build();
    }

}