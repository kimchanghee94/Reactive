package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class DeferExample04 {
    public static void main(String[] args){
        Logger.info("# Start");

        //siwtchIfEmpty()에 파라미터로 입려되는 시퀀스는 업스트림에서 emit되는 데이터가 없을 경우 다운스트림에 emit함
        //불필요한 호출을 방지하기 위해 실제 필요한 시점에 데이터를 emit하도록 defer()를 사용한다
        Mono<Object> mono =
                Mono.empty()
                    .switchIfEmpty(Mono.defer(DeferExample04::sayDefault));

        TimeUtils.sleep(3000);
        mono.subscribe(Logger::onNext);
    }

    private static Mono<String> sayDefault(){
        Logger.info("# Say Hi");
        return Mono.just("Hi");
    }
}
