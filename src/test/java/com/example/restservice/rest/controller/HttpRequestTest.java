package com.example.restservice.rest.controller;

import com.example.restservice.external.numberfinder.entity.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void finder() {
        int number = 670201816;
        assertThat(this.restTemplate.getForObject(String.format("http://localhost:%d/number_finder?number=%d", port, number),
                Result.class));
    }
}
