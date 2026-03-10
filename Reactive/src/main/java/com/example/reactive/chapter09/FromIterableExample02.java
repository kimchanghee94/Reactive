package com.example.reactive.chapter09;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class FromIterableExample02 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.coins)
            .subscribe(coin -> Logger.onNext("coin 명: " + coin.getT1() + ", 현재가: " + coin.getT2()));
    }
}
