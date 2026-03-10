package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;

public class CreateExample02 {
    public static void main(String[] args){
        Logger.info("# start");
        CryptoCurrencyPriceEmitter priceEmitter = new CryptoCurrencyPriceEmitter();

        Flux.create((FluxSink<Integer> sink) -> {
            priceEmitter.setListener(new CryptoCurrencyPriceListener() {
                @Override
                public void onPrice(List<Integer> priceList) {
                    priceList.stream().forEach(price -> {
                        sink.next(price);
                    });
                }

                @Override
                public void onComplete() {
                    sink.complete();
                }
            });
        })
        .publishOn(Schedulers.parallel())
        .subscribe(
                Logger::onNext,
                error -> {},
                () -> Logger.info("# onComplete"));
        TimeUtils.sleep(3000L);

        priceEmitter.flowInto();

        TimeUtils.sleep(2000L);
        priceEmitter.complete();
        TimeUtils.sleep(100L);
    }
}
