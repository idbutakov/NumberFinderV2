package com.example.restservice.external.numberfinder.api;

import com.example.restservice.core.CoreProperties;
import com.example.restservice.external.numberfinder.entity.Code;
import com.example.restservice.external.numberfinder.entity.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class NumberFinder implements Finder {
    private final CoreProperties coreProperties;

    public Result findNumber(int input, int countFiles) throws IOException, InterruptedException {
        Result result = new Result();
        ExecutorService executor = Executors.newFixedThreadPool(countFiles);
        List<Callable<Result>> callableTasks = new ArrayList<>();
        for (int fileNumber = 0; fileNumber < countFiles; fileNumber++) {
            int finalFileNumber = fileNumber;
            Callable<Result> callableTask = () -> find(finalFileNumber, input);
            callableTasks.add(callableTask);
        }
        List<Future<Result>> futures = executor.invokeAll(callableTasks);
        return result;
    }

    private Result find(int fileNumber, int findNumber) {
        Result result = new Result();
        String fileName = String.format("%s%d.%s", coreProperties.getPath(), fileNumber, coreProperties.getExtension());
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            log.info("Открыли файл " + fileName);
            scanner.useDelimiter(", ");

            ArrayList<Integer> numberArray = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                numberArray.add(number);
            }

            Collections.sort(numberArray);

            int position = Collections.binarySearch(numberArray, findNumber);

            Code code = Code.valueOf(position);
            if (code.asInt() != 1) {
                result.setCode(code);
            }
            if (code == Code.OK) {
                result.getFileNames().add("out" + fileNumber + ".txt");
            }
            log.info("Обработали файл без ошибок" + fileName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
