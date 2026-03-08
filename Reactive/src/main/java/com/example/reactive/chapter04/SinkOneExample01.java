package com.example.reactive.chapter04;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkOneExample01 {
    public static void main(String[] args){
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", Sinks.EmitFailureHandler.FAIL_FAST);

        mono.subscribe(data -> Logger.onNext("Subscriber1 ", data));
        mono.subscribe(data -> Logger.onNext("Subscriber2 ", data));
    }
}