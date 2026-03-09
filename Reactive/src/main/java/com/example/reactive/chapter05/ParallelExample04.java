package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelExample04 {
    public static void main(String[] args){
        Flux.fromArray(new Integer[]{1,3,5,7,9,11,13,15,17,19})
                .parallel(4)    //쓰레드 생성 개수 지정
                .runOn(Schedulers.parallel())
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
