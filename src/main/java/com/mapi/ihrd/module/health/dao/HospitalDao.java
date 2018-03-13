package com.mapi.ihrd.module.health.dao;

import com.mapi.ihrd.module.health.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalDao extends JpaRepository<Hospital, String> {
}
