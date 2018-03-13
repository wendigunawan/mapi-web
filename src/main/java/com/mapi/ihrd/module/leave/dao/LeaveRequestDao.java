package com.mapi.ihrd.module.leave.dao;

import com.mapi.ihrd.module.leave.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestDao extends JpaRepository<LeaveRequest, String> {
}
