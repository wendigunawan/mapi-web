package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.EmploymentStatusDao;
import com.mapi.ihrd.module.employee.model.EmploymentStatus;
import com.mapi.ihrd.module.employee.service.EmploymentStatusService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class EmploymentStatusServiceImpl implements EmploymentStatusService {

    @Autowired
    EmploymentStatusDao employmentStatusDao;

    @Override
    public Collection<EmploymentStatus> find() {
        return employmentStatusDao.findAll();
    }

    @Override
    public void save(EmploymentStatus employmentStatus) {
        employmentStatus.setCreatedDate(DateUtil.now());
        employmentStatusDao.save(employmentStatus);
    }

    @Override
    public EmploymentStatus getById(String id) {
        return employmentStatusDao.findOne(id);
    }

    @Override
    public Page<EmploymentStatus> find(PageRequest pageRequest) {
        return employmentStatusDao.findAll(pageRequest);
    }
}
