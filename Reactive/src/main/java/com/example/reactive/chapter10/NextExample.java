package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class NextExample {
    public static void main(String[] args){
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .doOnNext(Logger::doOnNext)
            .filter(tuple -> tuple.getT1() == 2015)
            .next()
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
