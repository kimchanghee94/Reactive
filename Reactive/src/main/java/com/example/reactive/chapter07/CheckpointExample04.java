package com.example.reactive.chapter07;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class CheckpointExample04 {
    public static void main(String[] args){
        Flux.just(2,4,6,8)
                .zipWith(Flux.just(1,2,3,0),(x,y)->x/y)
                .checkpoint("CheckpointExample02.zipWith.checkpoint")
                .map(num -> num+2)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
