package com.mapi.ihrd.module.performance.dao;

import com.mapi.ihrd.module.performance.model.Performance;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PerformanceDao extends PagingAndSortingRepository<Performance, String>, QueryDslPredicateExecutor<Performance> {
}
