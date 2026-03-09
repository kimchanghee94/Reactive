package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class StepVerifierTimeBasedTestExample02 {
    @Test
    public void getCOVID19CountTest(){
        StepVerifier
                //12시간 기다릴 필요없이 가상시간으로 처리
                .withVirtualTime(() -> TimeBasedExample.getCOVID19Count(
                        Flux.interval(Duration.ofHours(12)).take(1)
                ))
                .expectSubscription()
                .thenAwait(Duration.ofHours(12))    //thenAwait은 해당 시간을 당겨온다? 라는데 일단은 이해해두자
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }
}
