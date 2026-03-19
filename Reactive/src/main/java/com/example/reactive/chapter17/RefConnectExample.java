package com.example.reactive.chapter17;

import com.example.reactive.utils.Logger;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class RefConnectExample {
    public static void main(String[] args) throws InterruptedException{
        Flux<Long> publisher = Flux.interval(Duration.ofMillis(500))
//                .publish().autoConnect(1);    //hot publisher
                .publish().refCount(1); //cold publisher
        Disposable disposable = publisher.subscribe(data -> Logger.info("# subscriber 1: {}", data));
        Thread.sleep(2100L);
        disposable.dispose();

        publisher.subscribe(data -> Logger.info("# subscriber 2: {}", data));
        Thread.sleep(2500L);
    }
}
