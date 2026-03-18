package com.example.reactive.chapter15;

import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import com.example.reactive.common.Book;

public class OnErrorReturnExample01 {
    public static void main(String[] args){
        getBooks().map(book -> book.getPenName().toUpperCase())
                .onErrorReturn("No pen name")
                .subscribe(Logger::onNext, Logger::onError);
    }

    public static Flux<Book> getBooks(){
        return Flux.fromIterable(SampleData.books);
    }
}
