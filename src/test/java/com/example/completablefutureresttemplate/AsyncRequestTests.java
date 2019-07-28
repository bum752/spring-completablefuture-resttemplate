package com.example.completablefutureresttemplate;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AsyncRequestTests {

    @Test
    public void 비동기_요청_테스트() throws InterruptedException {
        // 포스트맨 mock 서버
        // timestamp 응답
        String url = "https://8fa84377-dc57-4e48-836b-a72d50358559.mock.pstmn.io";

        CompletableFuture<ResponseEntity<String>> completableFuture = AsyncRequestClient
                .request(url, HttpMethod.GET, null, null, String.class);

        // 5초 대기
        long startAt = System.currentTimeMillis() / 1000;
        Thread.sleep(5000L);
        long endAt = System.currentTimeMillis() / 1000;

        ResponseEntity<String> response = completableFuture.join();

        String responseAtString = Optional.ofNullable(response.getBody()).orElseThrow(RuntimeException::new);
        long responseAt = Long.parseLong(responseAtString);

        // 응답으로 받은 시간은 스레드 시작과 종료 사이여야 함
        Assertions.assertThat(responseAt)
                .isBetween(startAt, endAt);
    }

}
