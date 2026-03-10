package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

public class DeferExample02 {
    public static void main(String[] args){
        Logger.info("# Start");

        Mono.just("Hello")
                .delayElement(Duration.ofSeconds(2))
                .switchIfEmpty(sayDefault())
                .subscribe(Logger::onNext);

        TimeUtils.sleep(2500);
    }

    private static Mono<String> sayDefault(){
        Logger.info("# Say Hi");
        return Mono.just("Hi");
    }
}
