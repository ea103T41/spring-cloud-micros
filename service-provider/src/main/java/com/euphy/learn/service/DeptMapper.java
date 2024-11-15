package com.euphy.learn.service;

import com.euphy.learn.dto.DeptDto;
import com.euphy.learn.entity.Department;
import org.springframework.stereotype.Service;

@Service
public class DeptMapper {

    public Department mapToEntity(DeptDto dto) {
        return new Department(dto.id(), dto.name());
    }

    public DeptDto mapToDto(Department dept) {
        return new DeptDto(dept.getId(), dept.getName());
    }
}
