package com.example.reactive.chapter06;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class ContextAPIExample02 {
    public static void main(String[] args){
        String key1 = "id";
        String key2 = "name";
        String key3 = "country";

        Mono.deferContextual(ctx ->
                        Mono.just("ID: " + ctx.get(key1) + ", Name: " + ctx.get(key2) + ", Country: " + ctx.get(key3)))
                .publishOn(Schedulers.parallel())
                //putAll이 받을때 ContextView객체로 받아야함. 그래서 Context.of는 Context객체라 readOnly를 통해 ContextView객체로 전환한다.
                .contextWrite(context -> context.putAll(Context.of(key2, "Kevin", key3, "Korea").readOnly()))
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
