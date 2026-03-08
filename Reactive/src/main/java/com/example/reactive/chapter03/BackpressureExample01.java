package com.example.reactive.chapter03;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackpressureExample01 {
    public static void main(String[] args){
        //subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
        Flux.range(1,5)
                .doOnNext(Logger::doOnNext)
                .doOnRequest(Logger::doOnRequest)
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        TimeUtils.sleep(2000L);
                        Logger.onNext(value);
                        request(1);
                    }
                });
    }
}
