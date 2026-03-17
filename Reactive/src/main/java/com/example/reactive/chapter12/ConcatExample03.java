package com.example.reactive.chapter12;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.List;

public class ConcatExample03 {
    public static void main(String[] args){
        Flux.concat(
                Flux.fromIterable(getViralVectorVaccines()),
                Flux.fromIterable(getmRNAVaccines()),
                Flux.fromIterable(getSubunitVaccines())
        ).subscribe(Logger::onNext);
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getViralVectorVaccines(){
        return SampleData.viralVectorVaccines;
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getmRNAVaccines(){
        return SampleData.mRNAVaccines;
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getSubunitVaccines(){
        return SampleData.subunitVaccines;
    }
}
