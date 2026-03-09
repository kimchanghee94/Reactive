package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class ParallelExample01 {
    public static void main(String[] args){
        //이렇게만 parallel선언하면 병렬로 처리되지 않고 메인쓰레드가 단계별로 처리한다.
        Flux.fromArray(new Integer[]{1,3,5,7,9,11,13,15})
                .parallel()
                .subscribe(Logger::onNext);
    }
}
