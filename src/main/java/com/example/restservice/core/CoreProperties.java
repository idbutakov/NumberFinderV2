package com.example.restservice.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@ConfigurationProperties(prefix = "app")
@ConfigurationPropertiesScan
@Primary
@Data

public class CoreProperties {
    private String path;
    private String extension;
    private Integer countFile;
}
