package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class SkipExample03 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
            .filter(tuple -> tuple.getT2() >= 20_000_000)
            .skip(2)
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
