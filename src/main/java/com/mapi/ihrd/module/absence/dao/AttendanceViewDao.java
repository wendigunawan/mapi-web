package com.mapi.ihrd.module.absence.dao;

import com.mapi.ihrd.module.absence.model.AttendanceView;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttendanceViewDao extends PagingAndSortingRepository<AttendanceView, String>, QueryDslPredicateExecutor<AttendanceView>{
}
