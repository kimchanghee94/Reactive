package com.example.reactive.chapter06;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextFeatureExample03 {
    public static void main(String[] args){
        String key1 = "id";

        //동일한 키에대해서 write할 경우, 해당 키에 대한 값을 덮어쓴다
        Mono.deferContextual(ctx -> Mono.just("ID: " + ctx.get(key1)))
            .publishOn(Schedulers.parallel())
            .contextWrite(context -> context.put(key1, "itWorld"))
            .contextWrite(context -> context.put(key1, "itVillage"))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
