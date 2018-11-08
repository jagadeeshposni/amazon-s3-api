package com.jagadeeshposni.amazon.s3.configuration;


import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties
@EnableAutoConfiguration
@Data
public class YAMLConfig {
    public Map<String, String> aws;
}
