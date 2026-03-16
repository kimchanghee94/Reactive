package com.example.reactive.chapter11;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

public class FlatMapExample04 {
    private static final int BUY_PRICE = 500;
    private static final int INVESTMENT_AMOUNT = 1000;

    public static void main(String[] args){
        Flux.just(Tuples.of(BUY_PRICE, INVESTMENT_AMOUNT))
            .flatMap(buyInfo -> calculateMaxProfitPerYear(buyInfo))
            .subscribe(Logger::onNext);
    }

    private static Flux<Long> calculateMaxProfitPerYear(Tuple2<Integer, Integer> buyInfo){
        return Flux.fromIterable(SampleData.btcTopPricesPerYear)
                .map(btcInfo -> btcInfo.getT2()*buyInfo.getT2()/buyInfo.getT1() - buyInfo.getT2());
    }
}
