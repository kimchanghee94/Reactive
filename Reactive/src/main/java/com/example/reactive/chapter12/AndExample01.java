package com.example.reactive.chapter12;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import org.apache.commons.logging.Log;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class AndExample01 {
    public static void main(String[] args){
        //and연산자는 모두 emit안되고 complete만 호출하고 끝난다

        Mono.just("Okay")
            .delayElement(Duration.ofSeconds(1))
            .doOnNext(Logger::doOnNext)
            .and(
                Flux.just("Hi", "Tom")
                    .delayElements(Duration.ofSeconds(2))
                    .doOnNext(Logger::doOnNext)
            )
            .subscribe(
                Logger::onNext,
                Logger::onError,
                Logger::onComplete
            );

        TimeUtils.sleep(5000);
    }
}
