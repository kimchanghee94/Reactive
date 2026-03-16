package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class TakeUntilOtherExample {
    public static void main(String[] args){
        Flux
            .interval(Duration.ofMillis(300))
            .takeUntilOther(doSomeTask())
            .subscribe(Logger::onNext);
        TimeUtils.sleep(2000);
    }
    private static Publisher<?> doSomeTask(){
        return Mono.empty().delay(Duration.ofSeconds(1));
    }
}
