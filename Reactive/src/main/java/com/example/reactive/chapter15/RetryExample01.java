package com.example.reactive.chapter15;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class RetryExample01 {
    public static void main(String[] args) throws InterruptedException{
        final int[] count = {1};
        Flux.range(1,3)
            .delayElements(Duration.ofSeconds(1))
            .map(num -> {
                if(num == 3 && count[0] == 1){
                    count[0]++;
                    TimeUtils.sleep(1000);
                }
                return num;
            })
            .timeout(Duration.ofMillis(1500))
            .retry(1)
            .subscribe(Logger::onNext, Logger::onError, Logger::onComplete);

        TimeUtils.sleep(9000);
    }
}
