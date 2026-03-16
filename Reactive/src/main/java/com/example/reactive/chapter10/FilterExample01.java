package com.example.reactive.chapter10;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class FilterExample01 {
    public static void main(String[] args){
        Flux.range(1, 20)
                .filter(num -> num%2==0)
                .subscribe(Logger::onNext);
    }
}
