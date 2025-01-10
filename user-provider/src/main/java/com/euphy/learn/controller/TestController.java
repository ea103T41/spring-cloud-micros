package com.euphy.learn.controller;

import com.euphy.learn.service.RetryService;
import com.euphy.learn.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final RetryService retryService;
    private final TimeService timeService;

    @Autowired
    public TestController(RetryService retryService, TimeService timeService) {
        this.retryService = retryService;
        this.timeService = timeService;
    }

    @GetMapping("/retry")
    public void retry() {
        retryService.retry();
    }

    @GetMapping("/time")
    public String time() {
        return timeService.getTime();
    }
}
