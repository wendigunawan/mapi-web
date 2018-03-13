package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.aauth.model.User;
import com.mapi.ihrd.module.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface EmployeeService {

    void save(Employee employee);

    Employee findById(String id);

    Employee findByNik(String nik);

    Collection<Employee> find();

    Page<Employee> find(int limit, int offset);

    Page<Employee> find(PageRequest pageRequest);

    User createAccount(String id);
}
