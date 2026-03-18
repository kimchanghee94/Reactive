package com.example.reactive.chapter15;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorExample01 {
    public static void main(String[] args){
        Flux.range(1,5)
            .flatMap(num -> {
                if((num*2)%3==0){
                    return Mono.error(new IllegalArgumentException("Not allowed multiplication of 3"));
                }else{
                    return Mono.just(num * 2);
                }
            })
            .subscribe(
                Logger::onNext,
                Logger::onError
            );
    }
}
