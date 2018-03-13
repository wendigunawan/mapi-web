package com.mapi.ihrd.module.absence.dao;

import com.mapi.ihrd.module.absence.model.OvertimeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvertimeRequestDao extends PagingAndSortingRepository<OvertimeRequest, String> {
}
