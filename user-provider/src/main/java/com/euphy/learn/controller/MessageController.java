package com.euphy.learn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
@RefreshScope
public class MessageController {
  @Value("${message}")
  private String message;

  @GetMapping
  public String testConfig() {
    return message;
  }
}
