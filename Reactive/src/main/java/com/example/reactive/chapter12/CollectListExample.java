package com.example.reactive.chapter12;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class CollectListExample {
    public static void main(String[] args){
        Flux.just("...","---","...")
            .map(code -> transformMorseCode(code))
            .collectList()
            .subscribe(list -> Logger.onNext(String.join("",list)));
    }

    public static String transformMorseCode(String morseCode){
        return SampleData.morseCodeMap.get(morseCode);
    }
}
