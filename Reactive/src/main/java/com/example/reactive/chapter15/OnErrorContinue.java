package com.example.reactive.chapter15;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class OnErrorContinue {
    public static void main(String[] args){
        //권장되지 않는 메서드로 명확하지 않은 시퀀스의 동작으로 개발자가 의도치 않은 상황을 발생시킬 수 있다.
        //0으로 인한 에러발생시 다시 upstream으로 전송하는데
        //일반적인 flow는 에러발생시 downstream의 onerror발생하고 upstream으로 가는게 일반적이다.
        Flux.just(1,2,4,0,6,12)
            .map(num -> 12 /num)
            .onErrorContinue((error, num) -> Logger.onError("error: {}, num: {}", error, num))
            .subscribe(Logger::onNext, Logger::onError);

    }
}
