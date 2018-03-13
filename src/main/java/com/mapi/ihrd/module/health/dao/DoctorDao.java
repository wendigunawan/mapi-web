package com.mapi.ihrd.module.health.dao;

import com.mapi.ihrd.module.health.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, String> {
}
