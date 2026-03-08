package com.example.reactive.chapter04;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyExample05 {
    public static void main(String[] args){
        Sinks.Many<Integer> unicastSink = Sinks.many().replay().all();
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));
        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}