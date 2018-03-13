package com.mapi.ihrd.module.assurance.dao;

import com.mapi.ihrd.module.assurance.model.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AssuranceDao extends PagingAndSortingRepository<Assurance, String>, QueryDslPredicateExecutor<Assurance> {
}
