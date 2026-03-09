package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SchedulersNewBoundedExample01 {
    public static void main(String[] args){
        Scheduler scheduler = Schedulers.newBoundedElastic(2,2,"I/O-Thread");
        Mono<Integer> mono = Mono.just(1).subscribeOn(scheduler);

        Logger.info("# Start");

        mono.subscribe(data -> {
            Logger.onNext("subscribe 1 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 1 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 2 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 2 done", data);
        });

        mono.subscribe(data -> Logger.onNext("subscribe 3 doing", data));

        mono.subscribe(data -> Logger.onNext("subscribe 4 doing", data));
        mono.subscribe(data -> Logger.onNext("subscribe 5 doing", data));
        mono.subscribe(data -> Logger.onNext("subscribe 6 doing", data));

        //여기서 더 subscribe해버리면 공간이 없어서 예외가 발생한다.

//        TimeUtils.sleep(4000L);
//        scheduler.dispose();
    }
}
