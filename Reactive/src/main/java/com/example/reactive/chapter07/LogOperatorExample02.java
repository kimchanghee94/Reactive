package com.example.reactive.chapter07;

import com.example.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.HashMap;
import java.util.Map;

public class LogOperatorExample02 {
    public static Map<String, String> fruits = new HashMap<>();

    static{
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args){
        Hooks.onOperatorDebug();    //이거랑도 같이 쓸수있다, 다만 얘는 비용이 워낙 많이드는 기능이라 최후의 보류로 사용하자

        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
                .log()
                .map(String::toLowerCase)
                .log()
                .map(fruit -> fruit.substring(0, fruit.length()-1))
                .log()
                .map(fruits::get)
                .log()
                .subscribe(Logger::onNext, Logger::onError);
    }
}
