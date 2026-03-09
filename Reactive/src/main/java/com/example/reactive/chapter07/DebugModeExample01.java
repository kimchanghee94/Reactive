package com.example.reactive.chapter07;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class DebugModeExample01 {
    public static void main(String[] args){
        Flux.just(2,4,6,8)
                .zipWith(Flux.just(1,2,3,0),(x,y)->x/y)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
