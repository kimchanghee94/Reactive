package com.example.reactive.chapter06;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextFeatureExample01 {
    public static void main(String[] args){
        String key1 = "id";

        Mono<String> mono = Mono.deferContextual(ctx -> Mono.just("ID: " + ctx.get(key1)))
                .publishOn(Schedulers.parallel());

        //구독이 발생할때 마다 Context에 연결된다
        mono.contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(data -> Logger.onNext("subscriber 1", data));

        mono.contextWrite(context -> context.put(key1, "itWorld"))
                .subscribe(data -> Logger.onNext("subscriber 2", data));

        TimeUtils.sleep(100L);
    }
}
