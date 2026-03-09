package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class StepVerifierTimeBasedTestExample01 {
    @Test
    public void getCOVID19CountTest(){
        StepVerifier
                //12시간 기다릴 필요없이 가상시간으로 처리
                .withVirtualTime(() -> TimeBasedExample.getCOVID19Count(
                        Flux.interval(Duration.ofHours(12)).take(1)
                ))
                .expectSubscription()
                .then(()-> VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(12)))  //12시간을 앞당겨서 바로 테스트한다
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }
}
