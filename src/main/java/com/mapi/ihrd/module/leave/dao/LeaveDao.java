package com.mapi.ihrd.module.leave.dao;

import com.mapi.ihrd.module.leave.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveDao extends JpaRepository<Leave, String> {
}
