package com.euphy.learn;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
    options = options.excluding(
      ErrorAttributeOptions.Include.ERROR, ErrorAttributeOptions.Include.STACK_TRACE);

    return super.getErrorAttributes(request, options);
  }
}
