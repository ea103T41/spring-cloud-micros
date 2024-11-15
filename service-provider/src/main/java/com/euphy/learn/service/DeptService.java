package com.euphy.learn.service;

import com.euphy.learn.entity.Department;

import java.util.List;

public interface DeptService {

    Department getById(Long id);
    List<Department> list();
    void add(Department department);
}
