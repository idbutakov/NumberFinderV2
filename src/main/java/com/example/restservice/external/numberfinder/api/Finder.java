package com.example.restservice.external.numberfinder.api;

import com.example.restservice.external.numberfinder.entity.Result;

import java.io.IOException;

public interface Finder {
    Result findNumber(int input, int countFiles) throws IOException, InterruptedException;
}
