package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class SkipWhileExample01 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
            .skipWhile(tuple -> tuple.getT2() < 10_000_000)
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
