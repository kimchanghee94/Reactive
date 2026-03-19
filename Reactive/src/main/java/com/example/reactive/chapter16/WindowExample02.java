package com.example.reactive.chapter16;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.math.MathFlux;

public class WindowExample02 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.monthlyBookSales2021)
            .window(3)
                .flatMap(flux -> MathFlux.sumInt(flux))
                .subscribe(new BaseSubscriber<>() {
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
