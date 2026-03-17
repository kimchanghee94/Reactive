package com.example.reactive.chapter13;

import com.example.reactive.utils.Logger;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class DoAfterTerminateExample01 {
    public static void main(String[] args){
        //doOnTerminate는 upstream operator가 종료될때 호출된다.
        //doAfterTerminate는 전체 시퀀스가 종료할때 downStream에서 upstream으로 전파되면서 호출횐다.
        Flux.just("HI","HELLO")
            .filter(data -> data.equals("HI"))
            .doOnTerminate(() -> Logger.doOnTerminate("filter"))
            .doAfterTerminate(() -> Logger.doAfterTerminate("filter"))
            .map(data -> data.toLowerCase())
            .doOnTerminate(() -> Logger.doOnTerminate("map"))
            .doAfterTerminate(() -> Logger.doAfterTerminate("map"))
            .subscribe(
                data -> Logger.onNext(data),
                error -> {},
                () -> Logger.onComplete()
            );
    }
}
