package com.euphy.learn.service;

import com.euphy.learn.entity.Department;
import com.euphy.learn.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;

    @Autowired
    public DeptServiceImpl(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    public Department getById(Long id) {
        return deptRepository.getReferenceById(id);
    }

    public List<Department> list() {
        return deptRepository.findAll();
    }

    public void add(Department department) {
        deptRepository.save(department);
    }
}
