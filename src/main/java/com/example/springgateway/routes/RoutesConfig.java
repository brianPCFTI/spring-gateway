package com.example.springgateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class RoutesConfig {

    @Value("${uri.api.clientes}")
    private String clientesUri;

    @Bean
    public RouteLocator clientesRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("all_clientes",
                        r -> r.path("/v1/api/cliente/all")
                                .filters(
                                        f -> f.addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY_REQUEST")
                                                .addResponseHeader("X-GATEWAY-RESPONSE-HEADER", "GATEWAY_RESPONSE"))
                                .uri(clientesUri))
                .route("all_clientes",
                        r -> r.path("/promerica/v1/api/cliente/all")
                                .and().method(HttpMethod.GET)
                                .filters(
                                        f -> f.rewritePath("/promerica/v1/api/cliente/all", "/v1/api/cliente/all")
                                                .addRequestHeader("X-GATEWAY-REQUEST-HEADER", "GATEWAY_REQUEST")
                                                .addResponseHeader("X-GATEWAY-RESPONSE-HEADER", "GATEWAY_RESPONSEDEALBERTOSALAZAR"))
                                .uri(clientesUri))
                .build();
    }
}


