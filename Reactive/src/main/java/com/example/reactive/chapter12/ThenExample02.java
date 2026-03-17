package com.example.reactive.chapter12;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ThenExample02 {
    public static void main(String[] args){
        restartApplicationServer()
                .then()
                .subscribe(
                    Logger::onNext,
                    Logger::onError,
                    () -> Logger.onComplete("Send an email to Administrator: "
                            + "Application Server is restarted successfully")
                );

        TimeUtils.sleep(3000L);
    }

    private static Mono<Void> restartApplicationServer(){
        return Mono.just("Application Server was restarted successfully.")
                .delayElement(Duration.ofSeconds(2))
                .doOnNext(Logger::doOnNext)
                .flatMap(notUse -> Mono.empty());
    }
}
