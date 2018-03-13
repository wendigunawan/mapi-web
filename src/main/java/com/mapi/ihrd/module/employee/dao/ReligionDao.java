package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Religion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReligionDao extends PagingAndSortingRepository<Religion, String> {

    Page<Religion> findByDeleted(boolean deleted, Pageable pageable);
}
