package com.example.reactive.chapter15;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.DataFormatException;

public class ErrorExample02 {
    public static void main(String[] args){
        Flux.just('a','b','c','3','d')
            .flatMap(ErrorExample02::convert)
            .subscribe(
                Logger::onNext,
                Logger::onError
            );
    }

    private static Mono<String> convert(char ch){
        if(!Character.isAlphabetic(ch)){
            return Mono.error(new DataFormatException("Not Alphabetic"));
        }
        return Mono.just("Converted to " + Character.toUpperCase(ch));
    }
}
