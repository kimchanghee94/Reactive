package com.example.reactive.chapter12;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class WhenExample02 {
    public static void main(String[] args){
        Mono.when(restartApplicationServer(), restartDBServer(), restartStorageServer())
            .subscribe(
                Logger::onNext,
                Logger::onError,
                () -> Logger.onComplete("Send an email to Administrator: " +
                        "All Servers are restarted successfully")
            );

        TimeUtils.sleep(6000L);
    }

    private static Mono<String> restartApplicationServer(){
        return Mono
                .just("Application Server was restarted successfully.")
                .delayElement(Duration.ofSeconds(2))
                .doOnNext(Logger::doOnNext);
    }

    private static Publisher<String> restartDBServer(){
        return Mono
                .just("DB Server was restarted successfully.")
                .delayElement(Duration.ofSeconds(4))
                .doOnNext(Logger::doOnNext);
    }

    private static Mono<String> restartStorageServer(){
        return Mono.just("Storage Server was restarted successfully.")
                .delayElement(Duration.ofSeconds(3))
                .doOnNext(Logger::doOnNext);
    }
}
