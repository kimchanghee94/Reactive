package com.example.reactive.chapter07;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

public class DebugModeExample02 {
    public static void main(String[] args){
        //먼저 호출해야 디버그 모드가 된다
        Hooks.onOperatorDebug();
        Flux.just(2,4,6,8)
                .zipWith(Flux.just(1,2,3,0),(x,y)->x/y)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
