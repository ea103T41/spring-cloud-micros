package com.euphy.learn;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public User findByName(@PathVariable(name = "name") String name) {
        return userRepository.findByName(name);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(name = "id") Integer id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }
}
