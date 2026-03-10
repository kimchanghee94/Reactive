package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Mono;

public class JustOrEmptyExample01 {
    public static void main(String[] args){
        Mono.just(null).log().subscribe(Logger::onNext);
    }
}
