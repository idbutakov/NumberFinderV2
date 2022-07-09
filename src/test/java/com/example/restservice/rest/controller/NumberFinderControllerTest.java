package com.example.restservice.rest.controller;

import com.example.restservice.external.numberfinder.entity.Code;
import com.example.restservice.external.numberfinder.entity.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NumberFinderControllerTest {
    @Autowired
    private NumberFinderController numberFinderController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(numberFinderController).isNotNull();
    }

    @Test
    void finder() {
        int number = 670201816;
        Result res = new Result(Code.OK, Collections.singletonList("out0.txt"), null);
        ResponseEntity responseEntity = numberFinderController.finder(number);
        assertEquals(res, responseEntity.getBody());
    }
}