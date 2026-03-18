package com.example.reactive.chapter15;

import com.example.reactive.common.Book;
import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class OnErrorResumeExample {
    public static void main(String[] args){
        final String keyword = "DDD";
        getBooksFromCache(keyword)
                .onErrorResume(error -> getBooksFromDatabase(keyword))
                .subscribe(data -> Logger.onNext(data.getBookName()),
                        Logger::onError);
    }

    private static Flux<Book> getBooksFromCache(final String keyword){
        return Flux.fromIterable(SampleData.books)
                .filter(book -> book.getBookName().contains(keyword))
                .switchIfEmpty(Flux.error(new NoSuchBookException("No such Book")));
    }

    private static Flux<Book> getBooksFromDatabase(final String keyword){
        List<Book> books = new ArrayList<>(SampleData.books);
        books.add(new Book(11, "DDD: Domain Driven Design",
                "Joy", "ddd-man", 35000, 200));

        return Flux.fromIterable(books)
                .filter(book -> book.getBookName().contains(keyword))
                .switchIfEmpty(Flux.error(new NoSuchBookException("No such Book")));
    }

    private static class NoSuchBookException extends RuntimeException{
        NoSuchBookException(String message){
            super(message);
        }
    }
}
