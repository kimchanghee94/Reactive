package com.example.reactive.chapter12;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class MergeExample01 {
    public static void main(String[] args){
        Flux.merge(
                Flux.just(1,2,3).delayElements(Duration.ofMillis(300L)),
                Flux.just(4,5,6).delayElements(Duration.ofMillis(500L))
        ).subscribe(Logger::onNext);

        TimeUtils.sleep(3500L);
    }
}
