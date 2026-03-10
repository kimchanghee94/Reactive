package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class GenerateExample01 {
    public static void main(String[] args){
        Flux.generate(() -> 0, (state, sink)->{
            sink.next(state);
            if(state==10)
                sink.complete();
            return ++state;
        }).subscribe(Logger::onNext);
    }
}
