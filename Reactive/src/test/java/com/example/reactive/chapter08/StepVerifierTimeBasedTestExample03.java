package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class StepVerifierTimeBasedTestExample03 {
    @Test
    public void getCOVID19CountTest(){
        StepVerifier
                .withVirtualTime(() -> TimeBasedExample.getCOVID19Count(
                        Flux.interval(Duration.ofMinutes(1)).take(1)
                ))
                .expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3));     //3초안에 모든 시험이 끝나는지
    }
}
