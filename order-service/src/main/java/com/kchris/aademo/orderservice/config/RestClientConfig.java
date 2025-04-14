package com.kchris.aademo.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

  @Bean("userServiceRestClient")
  public RestClient userServiceRestClient(RestClient.Builder builder) {
    return builder
        .baseUrl("http://localhost:8081") // TODO -> Adjust to your user-service port
        .build();
  }

  @Bean("productServiceRestClient")
  public RestClient productServiceRestClient(RestClient.Builder builder) {
    return builder
        .baseUrl("http://localhost:8082") // TODO -> Adjust to your product-service port
        .build();
  }
}
