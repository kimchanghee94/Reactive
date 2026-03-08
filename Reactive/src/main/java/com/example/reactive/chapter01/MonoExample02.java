package com.example.reactive.chapter01;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Mono;

public class MonoExample02 {
    public static void main(String[] args){
        Mono.empty()
                .subscribe(
                        data -> Logger.info("# emitted data: {}", data),
                        error -> {},
                        () -> Logger.info("# emitted onComplete Signal")
                );
    }
}
