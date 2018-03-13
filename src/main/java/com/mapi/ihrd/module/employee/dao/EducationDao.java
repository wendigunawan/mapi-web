package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EducationDao extends PagingAndSortingRepository<Education, String> {

    Page<Education> findByDeleted(boolean deleted, Pageable pageable);

}
