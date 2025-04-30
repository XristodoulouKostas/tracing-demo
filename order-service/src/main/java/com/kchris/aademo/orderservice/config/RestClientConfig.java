package com.kchris.aademo.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

  @Value("${external.user-service.url}")
  private String userServiceUrl;

  @Value("${external.product-service.url}")
  private String productServiceUrl;

  @Bean("userServiceRestClient")
  public RestClient userServiceRestClient(RestClient.Builder builder) {
    return builder
        .baseUrl(userServiceUrl)
        .build();
  }

  @Bean("productServiceRestClient")
  public RestClient productServiceRestClient(RestClient.Builder builder) {
    return builder
        .baseUrl(productServiceUrl)
        .build();
  }
}
