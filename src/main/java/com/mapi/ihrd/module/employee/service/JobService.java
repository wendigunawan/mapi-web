package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Department;
import com.mapi.ihrd.module.employee.model.Job;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface JobService {

    void save(Job job);

    Job getById(String id);

    Collection<Job> find();

    Page<Job> find(PageRequest pageRequest);
}
