package com.example.reactive.chapter14;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class DelayElementsExample {
    public static void main(String[] args){
        Flux.range(1, 10)
            .doOnNext(Logger::doOnNext)
            .delayElements(Duration.ofMillis(500))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(6000);
    }
}
