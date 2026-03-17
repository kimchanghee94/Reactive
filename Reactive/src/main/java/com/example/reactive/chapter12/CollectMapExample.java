package com.example.reactive.chapter12;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class CollectMapExample {
    public static void main(String[] args){
        Flux.range(0, 26)
            .collectMap(
                    key -> SampleData.morseCodes[key],
                    value -> transformMorseCode(value)
            )
            .subscribe(map -> Logger.onNext(map));
    }

    public static String transformMorseCode(int value){
        return Character.toString((char)('a'+value));
    }
}
