package com.example.reactive.chapter15;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.DataFormatException;

public class ErrorExample03 {
    public static void main(String[] args){
        Flux.just('a','b','c','3','d')
            .flatMap(letter -> {
                try{
                    return convert(letter);
                }catch (DataFormatException e){
                    return Mono.error(e);
                }
            })
            .subscribe(
                Logger::onNext,
                Logger::onError
            );
    }

    private static Mono<String> convert(char ch) throws DataFormatException{
        CharacterValidator.isAlphabeticCharacter(ch);
        return Mono.just("Converted to " + Character.toUpperCase(ch));
    }
}
