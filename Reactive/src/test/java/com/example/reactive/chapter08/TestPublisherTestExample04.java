package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class TestPublisherTestExample04 {
    @Test
    public void divideByTwoTest(){
        //리액티브 사양을 위반하는 조건을 허용한다(아래 코드는 null값도 emit가능하도록 처리했다)
//        TestPublisher<Integer> src = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);
        TestPublisher<Integer> src = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.divideByTwo(src.flux()))
                .expectSubscription()
                .then(() -> src.next(2,4,6,8,null))
                .expectNext(1,2,3,4)
                .expectComplete()
                .verify();
    }
}
