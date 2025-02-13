package com.euphy.learn.controller;

import com.euphy.learn.dto.DeptDto;
import com.euphy.learn.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    private final DeptService deptService;

    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping("/dept/{id}")
    public DeptDto getDepartment(@PathVariable("id") Long id) {
        return deptService.getById(id);
    }

    @GetMapping("/dept/list")
    public List<DeptDto> getDepartments() {
        return deptService.list();
    }

    @PostMapping("/dept/add")
    public void addDepartment(@RequestBody String name) {
        deptService.add(new DeptDto(null, name));
    }
}
