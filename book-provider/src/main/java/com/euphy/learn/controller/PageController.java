package com.euphy.learn.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class PageController {

    @GetMapping("/")
    public String index() {
        return "Wellcome to Book Shop";
    }
}
