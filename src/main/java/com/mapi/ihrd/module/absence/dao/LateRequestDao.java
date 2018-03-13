package com.mapi.ihrd.module.absence.dao;

import com.mapi.ihrd.module.absence.model.LateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LateRequestDao extends JpaRepository<LateRequest, String> {
}
