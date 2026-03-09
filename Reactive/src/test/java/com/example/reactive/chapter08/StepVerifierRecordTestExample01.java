package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.Every.everyItem;
import java.util.ArrayList;

public class StepVerifierRecordTestExample01 {
    @Test
    public void getCountryTest(){
        StepVerifier
                .create(RecordExample.getCountry(Flux.just("france","russia","greece","poland")))
                .expectSubscription()
                .recordWith(ArrayList::new) //emit된 데이터를 기록하는 세션을 시작한다.
                .thenConsumeWhile(country -> !country.isEmpty())
                .consumeRecordedWith(countries -> {
                    assertThat(countries, everyItem(hasLength(6)));
                    assertThat(
                            countries.stream()
                                    .allMatch(country -> Character.isUpperCase(country.charAt(0))),
                            is(true)
                    );
                })
                .expectComplete()
                .verify();

    }
}
