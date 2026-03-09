package com.example.reactive.chapter06;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class ContextAPIExample03 {
    public static void main(String[] args){
        String key1 = "id";
        String key2 = "name";

        Mono.deferContextual(ctx ->
                        Mono.just("ID: " + ctx.get(key1)
                                + ", Name: " + ctx.get(key2)
                                + ", Country: " + ctx.getOrDefault("job", "SW Engineer")))
                .publishOn(Schedulers.parallel())
                .contextWrite(Context.of(key1, "itVillage", key2, "Kevin"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
