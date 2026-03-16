package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class SkipLastExample01 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
            .skipLast(2)
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
