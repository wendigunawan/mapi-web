package com.mapi.ihrd.module.employee.service.impl;


import com.mapi.ihrd.module.employee.dao.DepartmentDao;
import com.mapi.ihrd.module.employee.model.Department;
import com.mapi.ihrd.module.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;


    @Transactional(readOnly = true)
    @Override
    public Collection<Department> find() {
        return departmentDao.findAll();
    }

    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Transactional(readOnly = true)
    @Override
    public Department getById(String id) {
        return departmentDao.findOne(id);
    }

    @Override
    public Page<Department> find(PageRequest pageRequest) {
        return departmentDao.findAll(pageRequest);
    }
}
