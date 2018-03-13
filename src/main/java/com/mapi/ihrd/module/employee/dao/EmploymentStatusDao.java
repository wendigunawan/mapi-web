package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.EmploymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentStatusDao extends JpaRepository<EmploymentStatus, String> {
}
