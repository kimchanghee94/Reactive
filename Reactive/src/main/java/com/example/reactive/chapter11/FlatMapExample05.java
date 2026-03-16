package com.example.reactive.chapter11;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

public class FlatMapExample05 {

    public static void main(String[] args){
        Flux.just("Hello", "World")
            .map(word -> Mono.just(word))
            .flatMap(word -> word)
            .subscribe(Logger::onNext);
    }
}
