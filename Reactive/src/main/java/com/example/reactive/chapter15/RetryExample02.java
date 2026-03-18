package com.example.reactive.chapter15;

import com.example.reactive.common.Book;
import com.example.reactive.common.SampleData;
import com.example.reactive.utils.Logger;
import com.example.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;

public class RetryExample02 {
    public static void main(String[] args){
        //collect(Collectors.toSet())대신 collectList()로 해야되는 이유는
        //하나의 시퀀스에서 재시도를 하게 될경우 기존 collect에 담아둔 데이터에 실패하기 이전의 데이터가 중복으로 쌓이게된다
        getAllBooksFromRemoteDB()
            .collect(Collectors.toSet())
            .subscribe(
                bookSet -> bookSet.stream()
                                .forEach(book ->
                                        Logger.onNext("book name: {}, price: {}",
                                                book.getBookName(), book.getPrice()))
            );

        TimeUtils.sleep(12000);
    }

    private static Flux<Book> getAllBooksFromRemoteDB(){
        final int[] count = {0};
        return Flux.fromIterable(SampleData.books)
                .delayElements(Duration.ofMillis(500))
                .map(book -> {
                    try{
                        count[0]++;
                        if(count[0]==3)
                            Thread.sleep(2000);
                    }catch (InterruptedException e){

                    }
                    return book;
                })
                .timeout(Duration.ofSeconds(2))
                .doOnNext(book -> Logger.doOnNext(book.getBookName(), book.getPrice()))
                .retry(1);
    }
}
