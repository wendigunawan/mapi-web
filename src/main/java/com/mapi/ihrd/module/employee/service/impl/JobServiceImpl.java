package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.JobDao;
import com.mapi.ihrd.module.employee.model.Job;
import com.mapi.ihrd.module.employee.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
@Transactional
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Override
    public void save(Job job) {
        jobDao.save(job);
    }

    @Override
    public Job getById(String id) {
        return jobDao.findOne(id);
    }

    @Override
    public Collection<Job> find() {
        return jobDao.findAll();
    }

    @Override
    public Page<Job> find(PageRequest pageRequest) {
        return jobDao.findByDeleted(false, pageRequest);
    }
}
