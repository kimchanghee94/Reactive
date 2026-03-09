package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class StepVerifierGeneralTestExample01 {
    @Test
    public void sayHelloReactorTest(){
        StepVerifier
                .create(Mono.just("Hello Reactor")) //테스트 대상 Sequence 생성
                .expectNext("Hello Reactor")    //OnNext signal에 대한 emit된 데이터 검증
                .expectComplete()                   //onComplete Signal 검증
                .verify();                          //검증 실행.
    }
}
