package com.example.reactive.chapter17;

import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class PublishExample02 {
    private static ConnectableFlux<String> publisher;
    private static int checkedAudienceNumbers;
    static {
        publisher =
                Flux.just("Concert part1", "Concert part2", "Concert part3")
                    .delayElements(Duration.ofMillis(300L))
                    .publish();
    }

    public static void main(String[] args){
        checkAudienceNumbers();
        TimeUtils.sleep(500L);
        publisher.subscribe(data -> Logger.info("# audience 1 is watching {}", data));
        checkedAudienceNumbers++;

        checkAudienceNumbers();
        TimeUtils.sleep(500L);
        publisher.subscribe(data -> Logger.info("# audience 2 is watching {}", data));
        checkedAudienceNumbers++;

        checkAudienceNumbers();
        TimeUtils.sleep(500L);
        publisher.subscribe(data -> Logger.info("# audience 3 is watching {}", data));

        TimeUtils.sleep(1000L);
    }

    private static void checkAudienceNumbers(){
        if(checkedAudienceNumbers>=2){
            publisher.connect();
        }
    }
}
