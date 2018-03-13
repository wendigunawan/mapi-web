package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface DepartmentService {

    Collection<Department> find();

    void save(Department department);

    Department getById(String id);

    Page<Department> find(PageRequest pageRequest);

}

