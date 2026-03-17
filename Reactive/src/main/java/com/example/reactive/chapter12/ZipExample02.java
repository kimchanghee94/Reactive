package com.example.reactive.chapter12;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ZipExample02 {
    public static void main(String[] args){
        //zip은 tuple은 형태로 반환한다
        Flux.zip(
                Flux.just(1,2,3).delayElements(Duration.ofMillis(300)),
                Flux.just(4,5,6).delayElements(Duration.ofMillis(500)),
                (n1, n2) -> n1*n2
        )
        .subscribe(Logger::onNext);

        TimeUtils.sleep(2500L);
    }
}
