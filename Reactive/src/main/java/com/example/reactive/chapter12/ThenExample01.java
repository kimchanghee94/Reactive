package com.example.reactive.chapter12;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ThenExample01 {
    public static void main(String[] args){
        Mono.just("Hi")
            .delayElement(Duration.ofSeconds(1))
            .doOnNext(Logger::doOnNext)
            .then()
            .subscribe(
                Logger::onNext,
                Logger::onError,
                Logger::onComplete
            );

        TimeUtils.sleep(1500);
    }
}
