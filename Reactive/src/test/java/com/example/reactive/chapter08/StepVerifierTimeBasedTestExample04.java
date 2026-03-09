package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuples;

import java.time.Duration;

public class StepVerifierTimeBasedTestExample04 {
    @Test
    public void getCOVID19CountTest(){
        StepVerifier
                .withVirtualTime(() -> TimeBasedExample.getVoteCount(
                        Flux.interval(Duration.ofMinutes(1))
                ))
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))   //1분동안 어떤이벤트도 발생하지 않는가?
                .expectNext(Tuples.of("중구",15400))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(4) //위에서 이미 "중구"데이터 한개는 검증되었기때문
                .expectComplete()
                .verify();
    }
}
