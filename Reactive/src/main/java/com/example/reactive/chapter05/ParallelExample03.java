package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelExample03 {
    public static void main(String[] args){
        Flux.fromArray(new Integer[]{1,3,5,7,9,11,13,15,17,19})
                .parallel()
                .runOn(Schedulers.parallel())   //내 컴퓨터의 논리코어프로세스 개수만큼만 쓰레드가 생성된다
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
