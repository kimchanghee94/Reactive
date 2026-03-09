package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierGeneralTestExample04 {
    @Test
    public void divideByTwoTest(){
        Flux<Integer> flux = Flux.just(2,4,6,8,10);

        StepVerifier
                .create(GeneralExample.occurError(flux))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectError()
                .verify();
    }
}
