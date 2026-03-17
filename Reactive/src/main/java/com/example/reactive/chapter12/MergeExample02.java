package com.example.reactive.chapter12;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MergeExample02 {
    public static void main(String[] args){
        String[] usaStates = {
                "Ohio", "Michigan", "New Jersey", "Illinois", "New Hampshire",
                "Virginia", "Vermont", "North Carolina", "Ontario", "Georgia"
        };

        Flux.merge(getMeltDownRecoveryMessage(usaStates))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(3500L);
    }

    private static List<Mono<String>> getMeltDownRecoveryMessage(String[] usaStates){
        List<Mono<String>> messages = new ArrayList<>();

        for(String state : usaStates){
            messages.add(SampleData.nppMap.get(state));
        }

        return messages;
    }
}
