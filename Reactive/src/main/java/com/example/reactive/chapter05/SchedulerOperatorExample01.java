package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerOperatorExample01 {
    public static void main(String[] args){
        //메인 쓰레드로 처리
        Flux.fromArray(new Integer[]{1,3,5,7})
            .filter(data -> data>3)
            .map(data -> data*10)
            .subscribe(Logger::onNext);
    }
}
