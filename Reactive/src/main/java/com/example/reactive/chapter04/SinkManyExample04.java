package com.example.reactive.chapter04;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyExample04 {
    public static void main(String[] args){
        //구독 이후, emit된 데이터중에서 최신 데이터 2개만 replay한다.
        Sinks.Many<Integer> unicastSink = Sinks.many().replay().limit(2);
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));

        unicastSink.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}