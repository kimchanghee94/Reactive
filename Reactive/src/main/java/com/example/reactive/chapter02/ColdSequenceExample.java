package com.example.reactive.chapter02;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.Arrays;

public class ColdSequenceExample {
    public static void main(String[] args){
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("RED", "YELLOW", "PINK"))
                .map(String::toLowerCase);

        coldFlux.subscribe(data -> Logger.info("# Subscriber1: {}", data));
        Logger.info("--------------------");
        coldFlux.subscribe(data -> Logger.info("# Subscriber2: {}", data));
    }
}
