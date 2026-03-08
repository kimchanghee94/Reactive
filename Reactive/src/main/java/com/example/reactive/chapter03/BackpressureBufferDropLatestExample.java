package com.example.reactive.chapter03;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackpressureBufferDropLatestExample {
    private static int count = 0;
    public static void main(String[] args){
        //버퍼안에 데이터중에서 가장 최근에 버퍼로 들어온 데이터부터 drop시킨다.
        Flux.interval(Duration.ofMillis(300L))
                .doOnNext(data -> Logger.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(2,
                        dropped -> Logger.info("# Overflow & dropped: {}", dropped),
                        BufferOverflowStrategy.DROP_LATEST)
                .doOnNext(data -> Logger.info("# emitted by Buffer: {}", data))
                //prefetch는 추가되는 쓰레드를 1로 표시한다
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(
                        data -> {
                            TimeUtils.sleep(1000L);
                            Logger.onNext(data);
                        },
                        error -> Logger.onError(error)
                );
        TimeUtils.sleep(3000L);
    }
}
