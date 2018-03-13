package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeDao extends JpaRepository<Employee, String>, QueryDslPredicateExecutor<Employee> {
}
