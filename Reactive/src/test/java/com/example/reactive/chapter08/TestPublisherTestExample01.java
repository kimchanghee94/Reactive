package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class TestPublisherTestExample01 {
    @Test
    public void divideByTwoTest(){
        //테스트퍼블리셔로 처리하기
        TestPublisher<Integer> src = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.divideByTwo(src.flux()))
                .expectSubscription()
                .then(() -> src.next(2,4,6,8,10))
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }
}
