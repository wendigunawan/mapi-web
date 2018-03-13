package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.SkillDao;
import com.mapi.ihrd.module.employee.model.Skill;
import com.mapi.ihrd.module.employee.service.SkillService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillDao skillDao;

    @Override
    public Iterable<Skill> find() {
        return skillDao.findAll();
    }

    @Override
    public void save(Skill skill) {
        skill.setCreatedDate(DateUtil.now());
        skillDao.save(skill);
    }

    @Override
    public Skill getById(String id) {
        return skillDao.findOne(id);
    }

    @Override
    public Page<Skill> find(PageRequest pageRequest) {
        return skillDao.findByDeleted(false, pageRequest);
    }
}
