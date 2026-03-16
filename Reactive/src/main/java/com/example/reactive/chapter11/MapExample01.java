package com.example.reactive.chapter11;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class MapExample01 {
    public static void main(String[] args){
        Flux
            .just("Green-Circle", "Yellow-Circle", "Blue-Circle")
            .map(circle -> circle.replace("Circle", "Rectangle"))
            .subscribe(Logger::onNext);
    }
}
