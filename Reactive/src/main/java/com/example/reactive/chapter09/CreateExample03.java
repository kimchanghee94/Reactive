package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.sql.Time;
import java.util.List;

public class CreateExample03 {
    public static int start = 1;
    public static int end = 4;
    public static void main(String[] args){
        Flux.create((FluxSink<Integer> emitter) -> {
            emitter.onRequest(n -> {
                Logger.info("# requested: " + n);
                TimeUtils.sleep(500L);
                for(int i=start; i<=end; i++) emitter.next(i);
                start += 4;
                end += 4;
            });
            emitter.onDispose(()->{
                Logger.info("# clean up");
            });
        }, FluxSink.OverflowStrategy.DROP)
        .subscribeOn(Schedulers.boundedElastic())
        .publishOn(Schedulers.parallel(), 2)
        .subscribe(data -> {
            Logger.onNext(data);
        });
        TimeUtils.sleep(2500L);
    }
}
