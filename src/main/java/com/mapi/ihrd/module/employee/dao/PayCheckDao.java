package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.PayCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayCheckDao extends JpaRepository<PayCheck, String>{
}
