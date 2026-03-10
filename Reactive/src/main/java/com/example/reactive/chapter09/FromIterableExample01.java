package com.example.reactive.chapter09;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class FromIterableExample01 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.coinNames)
            .subscribe(Logger::onNext);
    }
}
