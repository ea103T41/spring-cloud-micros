package com.euphy.learn.controller;

import com.euphy.learn.User;
import com.euphy.learn.UserDto;
import com.euphy.learn.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public Mono<User> findByName(@PathVariable(name = "name") String name) {
        return userRepository.findByName(name);
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable(name = "id") Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public Mono<User> save(@RequestBody UserDto userDto) {
        if (userDto.getName() == null || userDto.getEmail() == null) {
            return Mono.error(new IllegalArgumentException("Name and Email are required"));
        }
        User user = User.builder().name(userDto.getName()).email(userDto.getEmail()).build();
        return userRepository.save(user)
          .onErrorResume(e -> Mono.error(new RuntimeException("Failed to save user", e)));
    }
}
