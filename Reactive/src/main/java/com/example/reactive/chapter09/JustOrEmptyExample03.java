package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class JustOrEmptyExample03 {
    public static void main(String[] args){
        Mono
            .justOrEmpty(Optional.ofNullable(null))
            .log()
            .subscribe(Logger::onNext);
    }
}
