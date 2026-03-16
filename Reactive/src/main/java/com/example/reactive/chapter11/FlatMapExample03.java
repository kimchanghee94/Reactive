package com.example.reactive.chapter11;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FlatMapExample03 {
    public static void main(String[] args){
        //비동기로 처리될 경우 emit되는 순서를 보장하지 않는다
        Flux.range(2, 8)
            .flatMap(dan -> Flux.range(1,9)
                    .publishOn(Schedulers.parallel())
                    .map(n -> dan + " * " + n + " = " + dan*n))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }
}
