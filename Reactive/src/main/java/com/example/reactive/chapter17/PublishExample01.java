package com.example.reactive.chapter17;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class PublishExample01 {
    public static void main(String[] args){
        ConnectableFlux<Integer> flux =
                Flux.range(1,5)
                    .delayElements(Duration.ofMillis(300L))
                    .publish();

        TimeUtils.sleep(500L);//어차피 coonnect가 되어야 발행시작이므로 그전에 sleep들은 의미없다
        flux.subscribe(data -> Logger.onNext("subscriber1: ", data));

        TimeUtils.sleep(200L);
        flux.subscribe(data -> Logger.onNext("subscriber2: ", data));

        flux.connect();

        TimeUtils.sleep(1000L);
        flux.subscribe(data -> Logger.onNext("subscriber3: ", data));

        TimeUtils.sleep(2000L);
    }
}
