package com.vk.user.handler;

import com.vk.user.Service.TransactionService;
import com.vk.user.dto.TransactionRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TransactionHandler {
    private final TransactionService transactionService;

    public TransactionHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Mono<ServerResponse> saveTransaction(ServerRequest request) {
        Mono<TransactionRequestDto> requestMono = request.bodyToMono(TransactionRequestDto.class);
        return requestMono
                .flatMap(transactionService::saveTransaction)
                .flatMap(responseDto -> ServerResponse.ok().bodyValue(responseDto))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("Error saving transaction: " + e.getMessage()));
    }

    public Mono<ServerResponse> findAllByUserId(ServerRequest request) {
        Integer userId = Integer.parseInt(request.pathVariable("userId"));
        return transactionService.findAllByUserId(userId)
                .collectList()
                .flatMap(responseDtoList -> ServerResponse.ok().bodyValue(responseDtoList))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("Error getting transactions: " + e.getMessage()));
    }

}
