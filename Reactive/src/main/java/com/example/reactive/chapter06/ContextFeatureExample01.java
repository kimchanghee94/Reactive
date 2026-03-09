package com.example.reactive.chapter06;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextFeatureExample01 {
    public static void main(String[] args){
        String key1 = "id";

        Mono<String> mono = Mono.deferContextual(ctx -> Mono.just("ID: " + ctx.getOrDefault(key1,"default")))
                .publishOn(Schedulers.parallel()).share();

        //share로 hot sequence로 처리하면 itVillage만 가능(값 덮어쓰기가 안되는 이유는 deferContextual은 시퀀스 첫 생성 시 한번만 처리 가능)
        //따라서 그 이후에 context.put은 새로운 컨텍스트를 반환하는 건데 이미 쓰기 타이밍은 종료된 시점이라 불가능
        //구독이 발생할때 마다 개별로 Context에 연결된다
        mono.contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(data -> Logger.onNext("subscriber 1", data));

        mono.contextWrite(context -> context.put(key1, "nono"))
                .subscribe(data -> Logger.onNext("subscriber 2", data));

        mono.subscribe(data -> Logger.onNext("subscriber 3", data));

        TimeUtils.sleep(100L);
    }
}
