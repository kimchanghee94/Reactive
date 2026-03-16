package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class FilterExample02 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
                .filter(tuple -> tuple.getT2()>10_000_000)
                .subscribe(filtered -> Logger.onNext(filtered.getT1() + ":" + filtered.getT2()));
    }
}
