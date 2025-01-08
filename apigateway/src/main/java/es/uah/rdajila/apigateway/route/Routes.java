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
    public RouterFunction<ServerResponse> userServiceRegisterRoute() {
        return GatewayRouterFunctions.route("userservice-register")
                .route(RequestPredicates.path("/api/signup/**"), HandlerFunctions.http("http://localhost:8082"))
                .before(rewritePath("%2520", " "))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceUsersRoute() {
        return GatewayRouterFunctions.route("userservice-users")
                .route(RequestPredicates.path("/api/usuarios/**"), HandlerFunctions.http("http://localhost:8082"))
                .before(rewritePath("%2520", " "))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRolesRoute() {
        return GatewayRouterFunctions.route("userservice-roles")
                .route(RequestPredicates.path("/api/roles/**"), HandlerFunctions.http("http://localhost:8082"))
                .before(rewritePath("%2520", " "))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceDocumentsRoute() {
        return GatewayRouterFunctions.route("userservice-documents")
                .route(RequestPredicates.path("/api/documentos/**"), HandlerFunctions.http("http://localhost:8082"))
                .before(rewritePath("%2520", " "))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> movieServiceRoute() {
        return GatewayRouterFunctions.route("movieservice-movies")
                .route(RequestPredicates.path("/api/peliculas/**"), HandlerFunctions.http("http://localhost:8081"))
                .before(rewritePath("%2520", " "))
                .route(RequestPredicates.path("/api/paises/**"), HandlerFunctions.http("http://localhost:8081"))
                .before(rewritePath("%2520", " "))
                .route(RequestPredicates.path("/api/generos/**"), HandlerFunctions.http("http://localhost:8081"))
                .before(rewritePath("%2520", " "))
                .route(RequestPredicates.path("/api/directores/**"), HandlerFunctions.http("http://localhost:8081"))
                .before(rewritePath("%2520", " "))
                .route(RequestPredicates.path("/api/actores/**"), HandlerFunctions.http("http://localhost:8081"))
                .before(rewritePath("%2520", " "))
                .build();
    }
}
