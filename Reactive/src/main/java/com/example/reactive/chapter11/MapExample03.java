package com.example.reactive.chapter11;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class MapExample03 {
    public static void main(String[] args){
        Flux.just("...","---","...")
            .map(code -> transformMorseCode(code))
            .subscribe(Logger::onNext);
    }

    public static String transformMorseCode(String morseCode){
        return SampleData.morseCodeMap.get(morseCode);
    }
}
