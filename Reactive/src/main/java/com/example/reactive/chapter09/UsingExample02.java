package com.example.reactive.chapter09;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class UsingExample02 {
    public static void main(String[] args){
        Path path = Paths.get("src/main/java/com/example/reactive/chapter09/using_example.txt");
        Flux
            .using(() -> Files.lines(path),
            stream -> Flux.fromStream(stream),
            Stream::close)
            .subscribe(Logger::onNext);
    }
}
