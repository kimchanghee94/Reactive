package com.example.reactive.chapter11;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FlatMapSequentialExample {
    public static void main(String[] args){
        //비동기로 처리되더라도 emit되는 순서를 보장한다(자주사용된다!!)
        Flux.range(2, 8)
            .flatMapSequential(dan -> Flux.range(1,9)
                    .publishOn(Schedulers.parallel())
                    .map(n -> dan + " * " + n + " = " + dan*n))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }
}
