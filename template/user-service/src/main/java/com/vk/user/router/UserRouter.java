package com.vk.user.router;
import com.vk.user.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


@Configuration
public class UserRouter {
    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
        return RouterFunctions.route()
                .path("/api/v1", builder ->
                        builder
                                .nest(accept(MediaType.APPLICATION_JSON),
                                        nestedBuilder -> nestedBuilder
                                                .GET("/users", userHandler::getAll)
                                                .GET("/user/{id}", userHandler::getUserById)
                                                .POST("/createuser", userHandler::saveUser)
                                                .PUT("/updateUser/{id}" , userHandler::updateUser)))
                .build();
    }
}
