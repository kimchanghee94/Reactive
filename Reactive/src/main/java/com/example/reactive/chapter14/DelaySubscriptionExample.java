package com.example.reactive.chapter14;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class DelaySubscriptionExample {
    public static void main(String[] args){
        Flux.range(1, 10)
            .doOnSubscribe(subscription -> Logger.info("# doOnSubscribe > upstream"))
            .delaySubscription(Duration.ofSeconds(2))
            .doOnSubscribe(subscription -> Logger.info("# doOnSubscribe > downstream"))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(2500);
    }
}
