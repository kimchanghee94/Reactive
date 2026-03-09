package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample02 {
    public static void main(String[] args){
        //publishOn()이 호출 이후의 체인은 다음 publishOn을 만나기전까지 publishOn에서 지정한 Thread에서 실행된다
        Flux.fromArray(new Integer[]{1,3,5,7})
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .publishOn(Schedulers.parallel())   //DownStream 쓰레드 지정
                .filter(data -> data>3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .map(data -> data*10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(500L);
    }
}
