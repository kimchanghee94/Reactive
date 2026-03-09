package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class StepVerifierGeneralTestExample02 {
    @Test
    public void sayHelloReactorTest(){
        StepVerifier
                .create(GeneralExample.sayHelloReactor()) //테스트 대상 Sequence 생성
                .expectSubscription()
                .expectNext("Hello")
                .expectNext("Reactor")
                .expectComplete()
                .verify();
    }
}
