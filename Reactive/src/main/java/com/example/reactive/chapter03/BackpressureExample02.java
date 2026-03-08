package com.example.reactive.chapter03;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackpressureExample02 {
    private static int count = 0;
    public static void main(String[] args){
        //subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
        Flux.range(1,5)
                .doOnNext(Logger::doOnNext)
                .doOnRequest(Logger::doOnRequest)
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(2);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        count++;
                        Logger.onNext(value);
                        if(count==2){
                            TimeUtils.sleep(2000L);
                            request(2);
                            count=0;
                        }

                    }
                });
    }
}
