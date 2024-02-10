package com.vk.user.router;


import com.vk.user.handler.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class TransactionRouter {
    @Bean
    public RouterFunction<ServerResponse> transactionRoutes(TransactionHandler transactionHandler) {
        return RouterFunctions.route()
                .path("/api/v1",builder ->
                        builder
                                .nest(accept(MediaType.APPLICATION_JSON),
                                        nestedBuilder -> nestedBuilder
                                                .POST("/transactions",transactionHandler::saveTransaction)
                                                .GET("/transactions/user/{userId}", transactionHandler::findAllByUserId)
                                )).build();
    }
}
