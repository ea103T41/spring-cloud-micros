package com.euphy.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discover")
@RequiredArgsConstructor
public class DiscoverController {

  private final DiscoveryClient client;

  @RequestMapping("/services")
  public Object services() {
    return client.getServices();
  }

  @RequestMapping("/{service}")
  public ServiceInstance instances(@PathVariable("service") String service) {
    return client.getInstances(service).get(0);
  }

}
