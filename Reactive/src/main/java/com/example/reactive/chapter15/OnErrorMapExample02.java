package com.example.reactive.chapter15;

import com.example.reactive.common.CannotDivideByZeroException;
import com.example.reactive.common.TimezoneNotFoundException;
import com.example.reactive.utils.Logger;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

public class OnErrorMapExample02 {
    private final static URI WORLD_TIME_URI = UriComponentsBuilder.newInstance().scheme("http")
            .host("worldtimeapi.org")
            .port(80)
            .path("/api/timezone/Asia/Mars")
            .build()
            .encode()
            .toUri();

    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Mono.fromSupplier(() -> restTemplate.exchange(WORLD_TIME_URI, HttpMethod.GET, new HttpEntity<String>(headers), String.class))
                .onErrorMap(HttpClientErrorException.class, (HttpClientErrorException ex) -> {
                    if(ex.getStatusCode() == HttpStatus.NOT_FOUND){
                        return new TimezoneNotFoundException(ex.getResponseBodyAsString());
                    }
                    return new HttpClientErrorException(ex.getStatusCode());
                })
                .map(response -> {
                    DocumentContext jsonContext = JsonPath.parse(response.getBody());
                    String dateTime = jsonContext.read("$.datetime");
                    return dateTime;
                })
                .subscribe(
                        Logger::onNext,
                        Logger::onError,
                        Logger::onComplete
                );
    }
}
