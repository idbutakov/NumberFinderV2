package com.example.restservice.rest.controller;

import com.example.restservice.core.CoreProperties;
import com.example.restservice.external.numberfinder.api.Finder;
import com.example.restservice.external.numberfinder.entity.Result;
import com.example.restservice.rest.entity.ResultEntity;
import com.example.restservice.rest.repository.ResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@RequiredArgsConstructor
public class NumberFinderController {
    private final ResultRepository resultRepository;
    private final Finder finder;
    private final CoreProperties coreProperties;

    @Autowired
    public NumberFinderController(ResultRepository resultRepository, Finder finder, CoreProperties coreProperties) {
        this.resultRepository = resultRepository;
        this.finder = finder;
        this.coreProperties = coreProperties;
    }

    @GetMapping("/number_finder")
    public ResponseEntity<?> finder(@RequestParam(value = "number", defaultValue = "815407325") Integer number) {
        try {
            Result result = finder.findNumber(number, coreProperties.getCountFile());
            ResultEntity resultEntity = new ResultEntity(result, number);
            resultRepository.save(resultEntity);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Нет файлов");
        }
    }
}
