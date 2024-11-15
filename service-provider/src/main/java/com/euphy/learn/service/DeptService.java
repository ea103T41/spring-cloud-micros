package com.euphy.learn.service;

import com.euphy.learn.dto.DeptDto;

import java.util.List;

public interface DeptService {

    DeptDto getById(Long id);
    List<DeptDto> list();
    void add(DeptDto dto);
}
