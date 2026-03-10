package com.example.reactive.chapter09;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class RangeExample01 {
    public static void main(String[] args){
        Flux.range(5,10)
            .subscribe(Logger::onNext);
    }
}
