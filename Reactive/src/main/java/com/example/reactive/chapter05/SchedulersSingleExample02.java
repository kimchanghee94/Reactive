package com.example.reactive.chapter05;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulersSingleExample02 {
    public static void main(String[] args){
        doTask("task1").subscribe(Logger::onNext);
        doTask("task2").subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName){
        return Flux.fromArray(new Integer[]{1,3,5,7})
                .doOnNext(data -> Logger.doOnNext(taskName, "fromArray", data))
                //new single()은 새로운 쓰레드로 할당해서 처리하기 때문에 쓰레드가 변경된다.
                .publishOn(Schedulers.newSingle("new-single", true))
                .filter(data -> data>3)
                .doOnNext(data -> Logger.doOnNext(taskName, "filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext(taskName, "map", data));
    }
}
