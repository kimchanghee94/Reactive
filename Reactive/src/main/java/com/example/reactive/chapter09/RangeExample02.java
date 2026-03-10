package com.example.reactive.chapter09;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.List;

public class RangeExample02 {
    public static void main(String[] args){
        List<String> coinNames = SampleData.coinNames;

        Flux.range(0,coinNames.size())
            .subscribe(index -> Logger.onNext(coinNames.get(index)));
    }
}
