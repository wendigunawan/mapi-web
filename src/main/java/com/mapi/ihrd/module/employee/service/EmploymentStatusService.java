package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Bank;
import com.mapi.ihrd.module.employee.model.EmploymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface EmploymentStatusService {
    Collection<EmploymentStatus> find();

    void save(EmploymentStatus employmentStatus);

    EmploymentStatus getById(String id);


    Page<EmploymentStatus> find(PageRequest pageRequest);
}
