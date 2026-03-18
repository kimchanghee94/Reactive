package com.example.reactive.chapter15;

import com.example.reactive.common.Book;
import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.IllegalFormatException;

public class OnErrorReturnExample02 {
    public static void main(String[] args){
        //구체적으로 catch타입 정의하기
        getBooks().map(book -> book.getPenName().toUpperCase())
                .onErrorReturn(NullPointerException.class, "No pen name")
                .onErrorReturn(IllegalFormatException.class, "Illegal pen name")
                .onErrorReturn(Exception.class, "Unknown error")
                .subscribe(Logger::onNext, Logger::onError);
    }

    public static Flux<Book> getBooks(){
        return Flux.fromIterable(SampleData.books);
    }
}
