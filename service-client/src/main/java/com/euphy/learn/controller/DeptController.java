package com.euphy.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeptController {

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    private final RestTemplate restTemplate;

    @Autowired
    public DeptController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/dept/list")
    public String getDeptList() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", String.class);
    }

    @GetMapping("/dept/{id}")
    public String getDeptById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/" + id, String.class);
    }

    @PostMapping("/dept/save")
    public String saveDept(@RequestParam("name") String name) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", name, String.class);
    }
}
