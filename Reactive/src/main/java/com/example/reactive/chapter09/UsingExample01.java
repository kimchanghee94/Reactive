package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class UsingExample01 {
    public static void main(String[] args){
        Mono.using(() -> "Resource",
                resource -> Mono.just(resource),
                resource -> Logger.info("cleanup: {}",resource))
                .subscribe(Logger::onNext);
    }
}
