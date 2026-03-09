package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * Backpressure ERROR 전략을 검증하는 예제
 *  - expectError()를 사용하여 에러가 발생되었는지 검증
 *  - verifyThenAssertThat()을 사용하여 검증 이후에 assertion method 를 사용하여 추가 검증을 할 수 있다.
 *  - hasDiscardedElements()를 사용하여 discard된 데이터가 있는지 검증한다. OverflowException이 발생할 때 2가 discard된다.
 *  - hasDiscarded()를 사용하여 discard된 데이터가 무엇인지 검증한다. OverflowException이 발생할 때 2가 discard된다.
 *  - hasDroppedElements()을 사용하여 Hooks.onNextDropped()으로 Drop된 데이터가 있는지를 검증한다.
 *  - hasDropped()를 사용하여 Hooks.onNextDropped()으로 Drop된 데이터가 무엇인지 검증한다.
 */

public class StepVerifierBackpressureTestExample02 {
    @Test
    public void generateNumberTest(){
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L)
                .thenConsumeWhile(num -> num>=1)    //emit된 데이터들을 소비한다
                .expectError()
                .verifyThenAssertThat()     //에러 발생이후 추가 조치 가능
                .hasDiscardedElements()     //해지된 데이터가 존재하는지?
                .hasDiscarded(2)    //어떤데이터가 폐기되었는가?
                .hasDroppedElements()       //drop된 데이터가 있어?
                .hasDropped(3,4,5,6,98,99,100); //얘네가 drop된거야?
    }
}
