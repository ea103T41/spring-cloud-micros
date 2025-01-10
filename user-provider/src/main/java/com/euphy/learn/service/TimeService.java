package com.euphy.learn.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class TimeService {

    @Cacheable(cacheNames = "getTime")
    public String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Cacheable("currentTimeMillis")
    public Long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
