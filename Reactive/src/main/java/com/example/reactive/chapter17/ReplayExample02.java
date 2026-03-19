package com.example.reactive.chapter17;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ReplayExample02 {
    public static void main(String[] args){
        ConnectableFlux<Integer> flux =
                Flux.range(1,5)
                    .delayElements(Duration.ofMillis(300L))
                    .replay(2); //가장 마지막에 이미 emit된 데이터 2개까지만 발행한다

        TimeUtils.sleep(500L);
        flux.subscribe(data -> Logger.onNext("subscriber1: ", data));

        TimeUtils.sleep(200L);
        flux.subscribe(data -> Logger.onNext("subscriber2: ", data));

        flux.connect();

        TimeUtils.sleep(1000L);
        flux.subscribe(data -> Logger.onNext("subscriber3: ", data));

        TimeUtils.sleep(2000L);
    }
}
