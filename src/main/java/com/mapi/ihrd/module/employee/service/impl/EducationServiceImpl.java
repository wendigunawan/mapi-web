package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.EducationDao;
import com.mapi.ihrd.module.employee.model.Education;
import com.mapi.ihrd.module.employee.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationDao educationDao;

    @Override
    public Education getById(String id) {
        return educationDao.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Education> find(PageRequest pageRequest) {
        return educationDao.findByDeleted(false, pageRequest);
    }

    @Override
    public void save(Education education) {
        educationDao.save(education);
    }

    @Override
    public Iterable<Education> findAll() {
        return educationDao.findAll();
    }
}
