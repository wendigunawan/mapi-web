package com.mapi.ihrd.module.absence.dao;

import com.mapi.ihrd.module.absence.model.Shift;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShiftDao extends PagingAndSortingRepository<Shift, String>, QueryDslPredicateExecutor<Shift>{
}
