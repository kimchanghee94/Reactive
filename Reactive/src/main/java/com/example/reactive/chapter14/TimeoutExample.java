package com.example.reactive.chapter14;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


public class TimeoutExample {
    public static void main(String[] args){
        requestToServer()
            .timeout(Duration.ofSeconds(2))
            .subscribe(
                    Logger::onNext,
                    Logger::onError
            );

        TimeUtils.sleep(3500);
    }

    private static Mono<String> requestToServer(){
        return Mono.just("Complete to process request from client successfully")
                .delayElement(Duration.ofSeconds(3));
    }
}
