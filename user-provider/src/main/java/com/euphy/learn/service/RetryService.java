package com.euphy.learn.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class RetryService {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Retryable(retryFor = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(value = 3000))
    public void retry() {
        int count = atomicInteger.incrementAndGet();
        log.info("count: {}", count);
        if (count < 5) {
            throw new RuntimeException();
        }
    }

    @Recover
    // maxAttempts exceeded
    public void recover(RuntimeException e) {
        log.info("get RuntimeException and retry too many times");
        throw new RuntimeException("Exceeded the maximum number of retries");
    }
}
