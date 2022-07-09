package com.example.restservice.external.documentgenerator.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Random;

@Slf4j
@Service
public class DocumentGenerator implements Generator {
    private static int COUNT = 100000000;
    private static int UPPER_BOUND = 1000000000;

    public void generate(int count) throws IOException {
        Random random = new Random();

        for (int fileNumber = 0; fileNumber < count; fileNumber++) {
            String filePath = "out" + fileNumber + ".txt";

            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < COUNT; i++) {
                int randomNumber = random.nextInt(UPPER_BOUND);
                printWriter.print(randomNumber);
                printWriter.print(", ");
            }

            printWriter.close();
        }
    }
}
