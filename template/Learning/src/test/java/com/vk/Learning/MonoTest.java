package com.vk.Learning;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoTest {
    @Test
    public void  monofluxtest() {
        Mono<String> monoString = Mono.just("Hello World")
                .log();
        monoString.subscribe(System.out::println);
    }
    @Test
    public void  monofluxExceptionTest() {
        Mono<?> monoString = Mono.just("Hello World")
                .then(Mono.error(new RuntimeException("error")))
                .log();
        monoString.subscribe(System.out::println, (e)-> System.out.println(e.getMessage()));
    }

    @Test
    public  void fluxSuccessTest() {
        Flux<?> fluxStrings = Flux.just("Hello","Good","Bad").log();
        fluxStrings.subscribe(System.out::println);
    }
    @Test
    public  void fluxExceptionTest() {
        Flux<?> fluxStrings = Flux.just("Hello","Good","Bad").
                concatWith(Flux.error(new RuntimeException("error"))).log();
        fluxStrings.subscribe(System.out::println, (e)-> System.out.println(e.getMessage()));
    }
}
