package com.example.reactive.chapter06;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ContextRealExample01 {
    public static final String HEADER_NAME_AUTH_TOKEN = "authToken";

    public static void main(String[] args){
        Mono<String> mono = postBook(Mono.just(
                new Book("abcd-1111-3533-2809", "Reactor's Bible", "Kevin"))
        ).contextWrite(Context.of(HEADER_NAME_AUTH_TOKEN, "eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));
        mono.subscribe(Logger::onNext);
    }

    private static Mono<String> postBook(Mono<Book> book){
        return Mono.zip(book, Mono.deferContextual(ctx ->  Mono.just(ctx.get(HEADER_NAME_AUTH_TOKEN))))
                .flatMap(tuple -> Mono.just(tuple))
                .flatMap(tuple -> {
                    String res = "POST the book(" + tuple.getT1().getBookName() +
                            ", " + tuple.getT1().getAuthor() + ") with token: " + tuple.getT2();
                    return Mono.just(res);
                });
    }
}
