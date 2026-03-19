package com.example.reactive.chapter16;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class BufferExample {
    public static void main(String[] args){
        Flux.range(1, 95)
            .buffer(10)
            .subscribe(buffer -> Logger.onNext(buffer));
    }
}
