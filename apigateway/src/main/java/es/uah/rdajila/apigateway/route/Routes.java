package es.uah.rdajila.apigateway.route;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> registerUserServiceRoute() {
        return GatewayRouterFunctions.route("regoister-user-service")
                .route(RequestPredicates.path("/api/signup/**"), HandlerFunctions.http("http://localhost:8082"))
                .before(rewritePath("%2520", " "))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServicesRoute() {
        return GatewayRouterFunctions.route("user-service")
                .route(RequestPredicates.path("/api/usuarios/**"), HandlerFunctions.http("http://localhost:8082"))
                .before(rewritePath("%2520", " "))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userRolServicesRoute() {
        return GatewayRouterFunctions.route("user-rol-service")
                .route(RequestPredicates.path("/api/roles/**"), HandlerFunctions.http("http://localhost:8082"))
                .before(rewritePath("%2520", " "))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userDocumentServicesRoute() {
        return GatewayRouterFunctions.route("user-rol-service")
                .route(RequestPredicates.path("/api/documentos/**"), HandlerFunctions.http("http://localhost:8082"))
                .before(rewritePath("%2520", " "))
                .build();
    }
}
