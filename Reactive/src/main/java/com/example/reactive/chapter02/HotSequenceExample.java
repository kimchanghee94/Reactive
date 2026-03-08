package com.example.reactive.chapter02;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

public class HotSequenceExample {
    public static void main(String[] args){
        Flux<String> concertFlux =
                Flux.fromStream(Stream.of("Single A", "Single B", "Single C", "Single D", "Single E"))
                        .delayElements(Duration.ofSeconds(1))
                        .share();   //cold를 hot으로 변환해주는 코드인데 원본 flux를 여러 sub가 공유하도록 해준다.
                                    //그리고 share메서드는 다른 쓰레드에서 돌아가게 된다. 따라서 메인쓰레드 sleep필요

        concertFlux.subscribe(singer -> Logger.info("# Subscriber1 is watching {}'s song.", singer));
        TimeUtils.sleep(2500);

        concertFlux.subscribe(singer -> Logger.info("# Subscriber2 is watching {}'s song.", singer));
        TimeUtils.sleep(3000);
    }
}
