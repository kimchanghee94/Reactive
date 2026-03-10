package com.example.reactive.chapter09;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Map;

public class GenerateExample03 {
    public static void main(String[] args){
        Map<Integer, Tuple2<Integer, Long>> map = SampleData.getBtcTopPricesPerYearMap();
        Flux.generate(()->2017,(state, sink)->{
            if(state>2021) sink.complete();
            else sink.next(map.get(state));
            return ++state;
        }).subscribe(Logger::onNext);
    }
}
