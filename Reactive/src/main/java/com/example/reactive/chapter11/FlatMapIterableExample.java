package com.example.reactive.chapter11;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.List;

public class FlatMapIterableExample {
    public static void main(String[] args){
        Flux.just(getViralVectorVaccines(), getmRNAVaccines(), getSubunitVaccines())
//            .map(vaccines -> vaccines)
            .flatMapIterable(vaccines -> vaccines)
            .subscribe(Logger::onNext);
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
