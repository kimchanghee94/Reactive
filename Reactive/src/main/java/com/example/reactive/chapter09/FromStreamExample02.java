package com.example.reactive.chapter09;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class FromStreamExample02 {
    public static void main(String[] args){
        Flux.fromStream(SampleData.coinNames.stream())
            .filter(coin -> coin.equals("BTC") || coin.equals("ETH"))
            .subscribe(Logger::onNext);
    }
}
