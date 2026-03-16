package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class SkipUntilOtherExample01 {
    public static void main(String[] args){

        //파라미터로 입력된 publisher가 onNext 또는 OnComplete Signal을 발생시킬 때까지 Upstream에서 emit된 데이터를 건너띈다.
        Flux.interval(Duration.ofSeconds(1))
            .skipUntilOther(doSomeTask())
            .subscribe(Logger::onNext);

        TimeUtils.sleep(4000);
    }

    private static Publisher<?> doSomeTask(){
        return Mono.empty().delay(Duration.ofMillis(2500));
    }
}
