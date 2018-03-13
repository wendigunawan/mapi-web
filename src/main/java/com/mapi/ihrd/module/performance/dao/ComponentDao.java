package com.mapi.ihrd.module.performance.dao;

import com.mapi.ihrd.module.performance.model.Component;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComponentDao extends PagingAndSortingRepository<Component, String>, QueryDslPredicateExecutor<Component> {
}
