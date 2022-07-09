package com.example.restservice.external.documentgenerator.api;

import java.io.IOException;

public interface Generator {
    void generate(int count) throws IOException;
}
