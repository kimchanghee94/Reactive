package com.example.reactive.chapter16;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

public class GroupByExample01 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.books)
            .groupBy(book -> book.getAuthorName())
            .flatMap(groupedFlux -> groupedFlux.map(book -> book.getBookName() +
                    "(" + book.getAuthorName() + ")").collectList()
            ).subscribe(Logger::onNext);
    }
}
