package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class TestPublisherTestExample03 {
    @Test
    public void divideByTwoTest(){
        //테스트퍼블리셔로 처리하기
        TestPublisher<Integer> src = TestPublisher.create();

        //emit과 next의 차이로는 emit 이후 complete를 호출함으로써 종료된다
        StepVerifier
                .create(src.flux().log())
                .expectSubscription()
                .then(() -> src.emit(1,2,3))
                .expectNext(1,2,3)
                .expectComplete()
                .verify();
    }
}
