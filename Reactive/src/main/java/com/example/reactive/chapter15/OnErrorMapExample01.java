package com.example.reactive.chapter15;

import com.example.reactive.common.CannotDivideByZeroException;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class OnErrorMapExample01 {
    public static void main(String[] args){
        Flux.just(1,3,0,6,8)
                .filter(num -> num%3==0)
                .doOnNext(Logger::doOnNext)
                .map(num -> (num*2)/num)
                .onErrorMap(error -> new CannotDivideByZeroException(error.getMessage()))
                .subscribe(Logger::onNext, Logger::onError);
    }
}
