package com.example.reactive.chapter04;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkManyExample01 {
    public static void main(String[] args){
        //unicast는 하나의 특정 시스템에만 전달한다. 즉, 하나의 subscribe에게만 데이터를 emit한다.
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));
        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        //두번째 구독자는 unicast로 인해 데이터를 발행받지 못하고 exception이 발생한다.
        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}