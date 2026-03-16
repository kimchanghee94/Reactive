package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class TakeLastExample {
    public static void main(String[] args){
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .takeLast(2)
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
