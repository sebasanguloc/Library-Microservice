package org.angulo.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator libraryServiceRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/api/book-service/**")
                        .filters( f -> f.rewritePath("/api/book-service/(?<segment>.*)","/${segment}")
                                .circuitBreaker(config -> config
                                        .setName("bookServiceCircuitBreaker")
                                        .setFallbackUri("forward:/support"))
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true)
                                )
                        )
                        .uri("lb://BOOK-SERVICE"))
                .route(p -> p
                        .path("/api/user-service/**")
                        .filters( f -> f.rewritePath("/api/user-service/(?<segment>.*)","/${segment}")
                                .circuitBreaker(config -> config
                                        .setName("userServiceCircuitBreaker")
                                        .setFallbackUri("forward:/support"))
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true)
                                )
                        )
                        .uri("lb://USER-SERVICE"))
                .route(p -> p
                        .path("/api/order-service/**")
                        .filters( f -> f.rewritePath("/api/order-service/(?<segment>.*)","/${segment}")
                                .circuitBreaker(config -> config
                                        .setName("orderServiceCircuitBreaker")
                                        .setFallbackUri("forward:/support"))
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true)
                                )
                        )
                        .uri("lb://ORDER-SERVICE"))
                .build();
    }

}
