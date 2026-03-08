package com.example.reactive.chapter01;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxExample04 {
    public static void main(String[] args){
        Flux.concat(
                Flux.just("Venus"),
                Mono.just("Earth"),
                Flux.just("Mars")
        )
        .collectList()  //하나의 리스트로 묶어서 반환한다. flux도 하나의 값으로 반환가능하다
        .subscribe(data -> Logger.info("# Solar System: {} ", data));
    }
}
