package com.example.reactive.chapter16;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GroupByExample03 {
    public static void main(String[] args){
        Flux.fromIterable(SampleData.books)
            .groupBy(book -> book.getAuthorName())
            .flatMap(groupedFlux ->
                    Mono.just(groupedFlux.key())
                        .zipWith(groupedFlux.map(book -> (int)(book.getPrice()*book.getStockQuantity()*0.1))
                        .reduce((y1,y2)->y1+y2),
                                (authorName, sumRoyalty) -> authorName + "'s royalty: " + sumRoyalty)
            ).subscribe(Logger::onNext);
    }
}
