package com.possumus.challenge.exception.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@Configuration
public class ErrorMessageConfiguration {

  private final Resource resource;

  public ErrorMessageConfiguration(@Value("classpath:error.properties") Resource resource) {
    this.resource = resource;
  }

  @Bean("errorMap")
  public Map<String, String> errorMap() throws IOException {
    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
    Map<String, String> errorMap = new HashMap<>();
    properties.forEach((key, value) -> errorMap.put(String.valueOf(key), String.valueOf(value)));
    return errorMap;
  }

}
