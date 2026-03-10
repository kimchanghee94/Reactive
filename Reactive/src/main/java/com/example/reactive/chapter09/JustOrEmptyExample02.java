package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Mono;

public class JustOrEmptyExample02 {
    public static void main(String[] args){
        Mono.justOrEmpty(null).log().subscribe(Logger::onNext);
    }
}
