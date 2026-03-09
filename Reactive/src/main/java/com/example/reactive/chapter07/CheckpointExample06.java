package com.example.reactive.chapter07;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class CheckpointExample06 {
    public static void main(String[] args){
        Flux.just(2,4,6,8)
                .zipWith(Flux.just(1,2,3,0),(x,y)->x/y)
                //아래처럼 할경우 assembly trace 정보를 출력해준다.
                .checkpoint("CheckpointExample05.zipWith.checkpoint",true)
                .map(num -> num+2)
                .checkpoint("CheckpointExample05.map.checkpoint",true)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
