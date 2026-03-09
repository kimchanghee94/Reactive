package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.Every.everyItem;

public class StepVerifierRecordTestExample02 {
    @Test
    public void getCountryTest(){
        StepVerifier
                .create(RecordExample.getCountry(Flux.just("france","russia","greece","poland")))
                .expectSubscription()
                .recordWith(ArrayList::new) //emit된 데이터를 기록하는 세션을 시작한다.
                .thenConsumeWhile(country -> !country.isEmpty()) //특정 조건에 부합하는 데이터만 데이터를 넣는다.
                // 테스트시작
                .expectRecordedMatches(countries ->
                        countries.stream().allMatch(country -> Character.isUpperCase(country.charAt(0))))
                .expectComplete()
                .verify();

    }
}
