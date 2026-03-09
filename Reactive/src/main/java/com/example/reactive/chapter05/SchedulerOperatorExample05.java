package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample05 {
    public static void main(String[] args){
        //subscribeOn, publishOn호출 순서로 제각각 할당된 쓰레드로 실행된다.
        Flux.fromArray(new Integer[]{1,3,5,7})
                .subscribeOn(Schedulers.boundedElastic())   //UpStream 쓰레드 지정
                .filter(data -> data>3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data*10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(500L);
    }
}
