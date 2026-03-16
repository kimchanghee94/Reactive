package com.example.reactive.chapter10;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import static com.example.reactive.common.CoronaVaccineService.isGreaterThan;

public class FilterWhenExample01 {
    public static void main(String[] args){
        //filterWhen을 통한 비동기적 처리
        Flux
            .fromIterable(SampleData.coronaVaccineNames)
            .filterWhen(vaccine -> isGreaterThan(vaccine, 3_000_000))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(1000);
    }
}
