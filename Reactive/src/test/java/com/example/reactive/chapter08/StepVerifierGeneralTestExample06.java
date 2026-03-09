package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class StepVerifierGeneralTestExample06 {
    @Test
    public void divideByTwoTest(){
        Flux<Integer> src = Flux.range(0,1000);

        StepVerifier
                .create(GeneralExample.takeNumber(src, 500),
                        StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(498)   //498개의 emit이 된 상태일때 다음 데이터는 499이다(nextCount없으면 expectNext몇백개 다써야됌)
                .expectNext(499)
                .verifyComplete();
    }
}
