package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample04 {
    public static void main(String[] args){
        //subscribeOn에 지정된 쓰레드로 업스트림과 다운스트림 모두 처리된다
        Flux.fromArray(new Integer[]{1,3,5,7})
                .subscribeOn(Schedulers.boundedElastic())   //UpStream 쓰레드 지정
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .filter(data -> data>3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .map(data -> data*10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(500L);
    }
}
