package com.example.reactive.chapter12;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class ConcatExample01 {
    public static void main(String[] args){
        Flux.concat(Flux.just(1,2,3), Flux.just(4,5))
            .subscribe(Logger::onNext);
    }
}
