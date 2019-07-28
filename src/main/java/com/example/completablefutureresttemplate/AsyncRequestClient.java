package com.example.completablefutureresttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

public class AsyncRequestClient {

    public static <T> CompletableFuture<ResponseEntity<T>> request(
            String url,
            HttpMethod httpMethod,
            HttpHeaders httpHeaders,
            String body,
            Class<T> responseType
    ) {
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        return CompletableFuture.completedFuture(
                restTemplate.exchange(url, httpMethod, httpEntity, responseType)
        );
    }

}
