package com.example.reactive.chapter10;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class TakeExample02 {
    public static void main(String[] args){
        Flux.interval(Duration.ofSeconds(1))
                .doOnNext(Logger::doOnNext)
                .take(Duration.ofSeconds(2))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(4000);
    }
}
