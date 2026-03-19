package com.example.reactive.chapter17;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AutoConnectExample {

    public static void main(String[] args) throws InterruptedException{
        Flux<String> publisher =
                Flux.just("Concert part1", "Concert part2", "Concert part3")
                    .delayElements(Duration.ofMillis(300L))
                    .publish()
                    .autoConnect(2);

        Thread.sleep(500L);
        publisher.subscribe(data -> Logger.info("# audience 1 is watching {}", data));

        Thread.sleep(500L);
        publisher.subscribe(data -> Logger.info("# audience 2 is watching {}", data));

        Thread.sleep(500L);
        publisher.subscribe(data -> Logger.info("# audience 3 is watching {}", data));

        Thread.sleep(1000L);
    }
}
