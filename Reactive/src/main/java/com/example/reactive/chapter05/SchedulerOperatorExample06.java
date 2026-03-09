package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample06 {
    public static void main(String[] args){
        //subscribeOn, publishOn호출 순서가 뒤바뀌어도 subscribeOn은 발행쓰레드만 신경쓰고 publishOn호출 이후는 다른 쓰레드!
        Flux.fromArray(new Integer[]{1,3,5,7})
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .publishOn(Schedulers.parallel())
                .filter(data -> data>3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .subscribeOn(Schedulers.boundedElastic())   //UpStream 쓰레드 지정
                .map(data -> data*10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(500L);
    }
}
