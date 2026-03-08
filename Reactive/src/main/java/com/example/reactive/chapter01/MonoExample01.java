package com.example.reactive.chapter01;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Mono;

public class MonoExample01 {
    public static void main(String[] args){
        //Mono는 RxJava에서 Maybe와 동일
        Mono.just("Heel Reactor!")
                .subscribe(data -> Logger.info("#emitted data: {}", data));
    }
}
