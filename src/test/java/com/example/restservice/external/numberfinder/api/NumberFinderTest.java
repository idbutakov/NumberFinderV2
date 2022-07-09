package com.example.restservice.external.numberfinder.api;

import com.example.restservice.core.CoreProperties;
import com.example.restservice.external.numberfinder.entity.Code;
import com.example.restservice.external.numberfinder.entity.Result;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NumberFinderTest {
    @Autowired
    private  Finder finder;
    @Autowired
    private CoreProperties coreProperties;

    @Test
    void findNumber() throws IOException, InterruptedException {
        int number=670201816;
        Result actual =new Result(Code.OK, Collections.singletonList("out0.txt"),null);
        Result ex = finder.findNumber(number,coreProperties.getCountFile());
        assertEquals(actual,ex);
    }
}