package com.example.reactive.chapter16;

import com.example.reactive.utils.Logger;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class WindowExample01 {
    public static void main(String[] args){
        Flux.range(1, 11)
            .window(3)
            .flatMap(flux -> {
                Logger.info("============================");
                return flux;
            })
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    subscription.request(2);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    Logger.onNext(value);
                    request(2);
                }
            });
    }
}
