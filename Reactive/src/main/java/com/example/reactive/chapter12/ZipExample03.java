package com.example.reactive.chapter12;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;

public class ZipExample03 {
    public static void main(String[] args){
        getInfectedPersonsPerHour(10, 21).subscribe(tuples -> {
                    Tuple3<Tuple2, Tuple2, Tuple2> t3 = (Tuple3) tuples;
                    int sum = (int) t3.getT1().getT2() + (int) t3.getT2().getT2() + (int) t3.getT3().getT2();
                    Logger.onNext(t3.getT1().getT1(), sum);
                });
        TimeUtils.sleep(6000L);
    }

    private static Flux getInfectedPersonsPerHour(int start, int end){
        return Flux.zip(
                Flux.fromIterable(SampleData.seoulInfected)
                        .filter(t2 -> t2.getT1() >= start && t2.getT1()<=end),
                Flux.fromIterable(SampleData.incheonInfected)
                        .filter(t2 -> t2.getT1() >= start && t2.getT1()<=end),
                Flux.fromIterable(SampleData.suwonInfected)
                        .filter(t2 -> t2.getT1() >= start && t2.getT1()<=end)
        );
    }
}
