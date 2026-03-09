package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelExample02 {
    public static void main(String[] args){
        Flux.fromArray(new Integer[]{1,3,5,7,9,11,13,15})
                .parallel()
                .runOn(Schedulers.parallel())   //실행쓰레드가 스케줄러를 진행해줘야한다.
                .subscribe(Logger::onNext);

        TimeUtils.sleep(1000L);
    }
}
