package com.example.reactive.chapter04;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyExample02 {
    public static void main(String[] args){
        //하나 이상의 subs에게 데이터를 emit한다.(hot sequence방식으로 동작한다)
        Sinks.Many<Integer> unicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));
        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));

        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

    }
}