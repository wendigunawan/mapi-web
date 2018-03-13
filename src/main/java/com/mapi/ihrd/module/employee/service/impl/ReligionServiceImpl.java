package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.ReligionDao;
import com.mapi.ihrd.module.employee.model.Religion;
import com.mapi.ihrd.module.employee.service.ReligionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class ReligionServiceImpl implements ReligionService {

    @Autowired
    private ReligionDao religionDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Religion> find(PageRequest pageRequest) {
        return this.find(pageRequest, false);
    }

    @Override
    public Page<Religion> find(PageRequest pageRequest, boolean deleted) {
        return religionDao.findByDeleted(deleted, pageRequest);
    }

    @Override
    public void save(Religion religion) {
        religionDao.save(religion);
    }

    @Override
    public Religion getById(String id) {
        return religionDao.findOne(id);
    }
}
