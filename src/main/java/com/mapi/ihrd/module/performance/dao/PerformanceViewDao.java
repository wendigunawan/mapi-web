package com.mapi.ihrd.module.performance.dao;

import com.mapi.ihrd.module.performance.model.PerformanceView;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PerformanceViewDao extends PagingAndSortingRepository<PerformanceView, String>, QueryDslPredicateExecutor<PerformanceView> {
}
