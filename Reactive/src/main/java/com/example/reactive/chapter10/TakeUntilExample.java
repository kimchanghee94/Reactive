package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class TakeUntilExample {
    public static void main(String[] args){
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .takeUntil(tuple -> tuple.getT2() > 10_000_000)
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
