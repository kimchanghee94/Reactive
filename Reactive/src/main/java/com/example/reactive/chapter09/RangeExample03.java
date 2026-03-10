package com.example.reactive.chapter09;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.List;

public class RangeExample03 {
    public static void main(String[] args){
        Flux.range(7,5)
                .map(idx -> SampleData.btcTopPricesPerYear.get(idx))
                .subscribe(tuple -> Logger.onNext(tuple.getT1() + "'s: " + tuple.getT2()));
    }
}
