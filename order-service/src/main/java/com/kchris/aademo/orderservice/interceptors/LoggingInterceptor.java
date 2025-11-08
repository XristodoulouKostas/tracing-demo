package com.kchris.aademo.orderservice.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // String correlationId = UUID.randomUUID().toString();
    // MDC.put("correlation-id", correlationId);
    // response.addHeader("Correlation-Id", correlationId);
    return true;
  }

}
