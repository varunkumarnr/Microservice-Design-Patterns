package com.vk.user.handler;

import com.vk.user.Service.UserService;
import com.vk.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return userService.findAll()
                .collectList()
                .flatMap(userDtoList -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userDtoList))
                .onErrorResume(Exception.class, e ->
                        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .bodyValue("Error getting users: " + e.getMessage()));
    }


    public Mono<ServerResponse> getUserById(ServerRequest request) {
        return Mono.just(request.pathVariable("id"))
                .flatMap(userIdString -> {
                    try {
                        Long userId = Long.parseLong(userIdString);
                        return userService.findById(userId);
                    } catch (NumberFormatException e) {
                        return Mono.error(e);
                    }
                })
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(NumberFormatException.class, e ->
                        ServerResponse.badRequest().bodyValue("Invalid user ID"))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("Error getting user" +e.getMessage()));
    }

    public Mono<ServerResponse> saveUser(ServerRequest request) {
        return  Mono.just(request.bodyToMono(UserDto.class))
                .flatMap(user ->
                        Mono.fromCallable(() -> userService.saveUser(user))
                                .flatMap(savedUser -> ServerResponse.ok().body(savedUser, UserDto.class))
                ).onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("Error Creating user" +e.getMessage()));
    }

    public Mono<ServerResponse> updateUser(ServerRequest request) {
        Mono<UserDto> requestUserMono = request.bodyToMono(UserDto.class);
        Long userId = Long.parseLong(request.pathVariable("id"));
        return userService.updateUser(requestUserMono, userId)
                .flatMap(savedUser -> ServerResponse.ok().bodyValue(savedUser))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("Error updating user: " + e.getMessage()))
                .log();
    }
}
