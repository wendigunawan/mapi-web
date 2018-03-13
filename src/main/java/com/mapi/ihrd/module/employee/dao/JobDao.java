package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDao extends JpaRepository<Job, String> {

    Page<Job> findByDeleted(boolean deleted, Pageable pageable);
}
