package com.example.reactive.chapter08;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class StepVerifierBackpressureTestExample03 {
    @Test
    public void generateNumberTest(){
        StepVerifier
                .create(BackpressureExample.generateNumberByDropStrategy(), 1L)
                .thenConsumeWhile(num -> num>=1)    //emit된 데이터들을 소비한다
                .expectComplete()
                .verifyThenAssertThat()     //에러 발생이후 추가 조치 가능
                .hasDiscardedElements()     //해지된 데이터가 존재하는지?
                .hasDiscarded(2)    //어떤데이터가 폐기되었는가?

                //실패하는 이유는 hasDropped는 backpressure drop을 증명하는 메서드가 아니다
                //backprsure는 discard로 처리된다.
                .hasDropped(3,4,5,6,98,99,100);

        /*
        hasDiscarded는 filter나 take와 같이 파이프라인 내부에서 걸러지는경우
        hasDropped는 오버플로우, 백프레셔로 인해 다운스트림으로 전달 실패한경우
         */
    }
}
