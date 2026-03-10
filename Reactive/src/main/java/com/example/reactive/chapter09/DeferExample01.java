package com.example.reactive.chapter09;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class DeferExample01 {
    public static void main(String[] args){
        Logger.info("# Starting");

        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        TimeUtils.sleep(2000);

        justMono.subscribe(data -> Logger.onNext("just1", data));
        deferMono.subscribe(data -> Logger.onNext("defer1", data));

        TimeUtils.sleep(2000);

        justMono.subscribe(data -> Logger.onNext("just2", data));
        deferMono.subscribe(data -> Logger.onNext("defer2", data));
    }
}
