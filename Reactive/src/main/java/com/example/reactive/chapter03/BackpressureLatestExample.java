package com.example.reactive.chapter03;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackpressureLatestExample {
    private static int count = 0;
    public static void main(String[] args){
        //drop과 latest의 차이는 drop전략은 버퍼가 가득 찬상태면 그 즉시 drop하고
        //latest는 버퍼가 가득 차있을때 최신 데이터가 바로 drop하지 않고 두다가 또 최신데이터가 밀려들어올때 삭제시킨다.
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                        TimeUtils.sleep(5L);
                        Logger.onNext(data);
                    },
                    error -> Logger.onError(error));

        TimeUtils.sleep(2000L);
    }
}
