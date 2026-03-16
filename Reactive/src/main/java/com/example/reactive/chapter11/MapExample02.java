package com.example.reactive.chapter11;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class MapExample02 {
    public static void main(String[] args){
        final double buyPrice = 40_000_000;
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
            .filter(tuple -> tuple.getT1() == 2021)
            .doOnNext(Logger::doOnNext)
            .map(tuple -> calculateProfitRate(buyPrice, tuple.getT2()))
            .subscribe(result -> Logger.onNext(result + "%"));
    }

    private static double calculateProfitRate(double buyPrice, long topPrice){
        return (topPrice - buyPrice)/buyPrice*100;
    }
}
