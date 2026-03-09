package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class StepVerifierGeneralTestExample03 {
    @Test
    public void sayHelloReactorTest(){
        StepVerifier
                .create(GeneralExample.sayHelloReactor())
                .expectSubscription()
                .as("# expect subscription")
                .expectNext("Hi")
                .as("# expect Hi")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }
}
