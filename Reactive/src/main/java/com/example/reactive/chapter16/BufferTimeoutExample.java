package com.example.reactive.chapter16;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BufferTimeoutExample {
    public static void main(String[] args){
        Flux.range(1,20)
            .map(num -> {
                try{
                    if(num<10){
                        Thread.sleep(100L);
                    }else{
                        Thread.sleep(300L);
                    }
                }catch (InterruptedException e){

                }
                return num;
            })
            .bufferTimeout(3, Duration.ofMillis(400L))
            .subscribe(buffer -> Logger.onNext(buffer));
    }
}
