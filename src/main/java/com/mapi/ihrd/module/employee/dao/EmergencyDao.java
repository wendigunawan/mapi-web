package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.EmployeeEmergency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyDao extends JpaRepository<EmployeeEmergency, String>{
}
