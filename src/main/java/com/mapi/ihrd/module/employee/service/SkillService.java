package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface SkillService {
    Iterable<Skill> find();

    void save(Skill skill);

    Skill getById(String id);

    Page<Skill> find(PageRequest pageRequest);
}
