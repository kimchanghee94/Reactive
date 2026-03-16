package com.example.reactive.chapter11;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class FlatMapExample02 {
    public static void main(String[] args){
        Flux.just(3)
            .flatMap(dan -> Flux.range(1,9).map(n -> dan + " * " + n + " = " + dan*n))
            .subscribe(Logger::onNext);
    }
}
