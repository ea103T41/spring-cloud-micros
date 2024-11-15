package com.euphy.learn.service;

import com.euphy.learn.dto.DeptDto;
import com.euphy.learn.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;
    private final DeptMapper deptMapper;

    @Autowired
    public DeptServiceImpl(DeptRepository deptRepository, DeptMapper deptMapper) {
        this.deptRepository = deptRepository;
        this.deptMapper = deptMapper;
    }

    public DeptDto getById(Long id) {
        return deptMapper.mapToDto(deptRepository.getReferenceById(id));
    }

    public List<DeptDto> list() {
        return deptRepository.findAll().stream().map(deptMapper::mapToDto).toList();
    }

    public void add(DeptDto deptDto) {
        deptRepository.save(deptMapper.mapToEntity(deptDto));
    }
}
