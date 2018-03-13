package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface EducationService {

    Education getById(String id);

    Page<Education> find(PageRequest pageRequest);

    void save(Education education);

    Iterable<Education> findAll();
}
