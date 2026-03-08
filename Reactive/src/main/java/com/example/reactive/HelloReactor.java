package com.example.reactive;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//리액터란 리액티브 프로그래밍을 위한 리액티브 라이브러리다
public class HelloReactor {
    public static void main(String[] args){
        Mono.just("Hello Reactor")
                .subscribe(message->System.out.println(message));

        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence.map(data -> data.toLowerCase())
                .subscribe(data -> Logger.onNext(data));
    }
}
