package com.example.reactive.chapter04;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkOneExample02 {
    public static void main(String[] args){
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", Sinks.EmitFailureHandler.FAIL_FAST);

        //단 한개의 데이터만 emit할 수 있기 때문에 두번째 emit한 데이터는 drop된다.
        sinkOne.emitValue("Hi Reactor", Sinks.EmitFailureHandler.FAIL_FAST);

        mono.subscribe(data -> Logger.onNext("Subscriber1 ", data));
        mono.subscribe(data -> Logger.onNext("Subscriber2 ", data));
    }
}