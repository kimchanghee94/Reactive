package com.example.reactive.chapter12;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.List;

public class ConcatExample02 {
    public static void main(String[] args){
        List<Flux<Integer>> sources = List.of(Flux.just(1,2,3), Flux.just(4,5,6));

        Flux.concat(sources)
            .subscribe(Logger::onNext);
    }
}
