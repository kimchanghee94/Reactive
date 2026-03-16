package com.example.reactive.chapter11;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class FlatMapExample01 {
    public static void main(String[] args){
        Flux.just("Good","Bad")
            .flatMap(feeling ->
                    Flux.just("Morning", "Afternoon", "Evening")
                        .map(time->feeling+" " +time))
            .subscribe(Logger::onNext);
    }
}
