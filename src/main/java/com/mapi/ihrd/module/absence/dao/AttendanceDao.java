package com.mapi.ihrd.module.absence.dao;

import com.mapi.ihrd.module.absence.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceDao extends PagingAndSortingRepository<Attendance, String>, QueryDslPredicateExecutor<Attendance> {
}
