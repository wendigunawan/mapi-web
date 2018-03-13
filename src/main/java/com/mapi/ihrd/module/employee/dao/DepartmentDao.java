package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends JpaRepository<Department, String> {
}
