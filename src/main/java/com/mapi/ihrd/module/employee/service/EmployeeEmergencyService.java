package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Education;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.model.EmployeeEmergency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface EmployeeEmergencyService {
    EmployeeEmergency getById(String id);

    Page<EmployeeEmergency> find(PageRequest pageRequest);

    Collection<EmployeeEmergency> find();

    Collection<EmployeeEmergency> getByEmployee(Employee employee);

    void save(EmployeeEmergency emergency);
}
