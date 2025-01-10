package com.euphy.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookProviderApplication.class, args);
        System.out.println("http://localhost:8003");
    }
}
