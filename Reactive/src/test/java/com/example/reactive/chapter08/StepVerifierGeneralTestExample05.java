package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import javax.crypto.spec.PSource;

public class StepVerifierGeneralTestExample05 {
    @Test
    public void divideByTwoTest(){
        Flux<Integer> src = Flux.just(2,4,6,8,10);

        StepVerifier
                .create(GeneralExample.divideByTwo(src))
                .expectSubscription()
                .expectNext(1,2,3,4,5)
                .verifyComplete();
    }
}
