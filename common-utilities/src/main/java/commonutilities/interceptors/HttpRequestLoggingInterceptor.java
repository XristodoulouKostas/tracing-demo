package commonutilities.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class HttpRequestLoggingInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    logHttpHeaders(request);
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  private void logHttpHeaders(HttpServletRequest request) {
    Map<String, String> headers = new HashMap<>();
    request.getHeaderNames()
        .asIterator()
        .forEachRemaining(header -> headers.put(header, request.getHeader(header)));
    HttpRequestLog httpLog = HttpRequestLog.builder()
        .method(request.getMethod())
        .uri(request.getRequestURI())
        .headers(headers)
        .build();
    log.info(httpLog.toString());
  }
}