package com.mapi.ihrd.module.absence.dao;

import com.mapi.ihrd.module.absence.model.OvertimeRequestView;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OvertimeRequestViewDao extends PagingAndSortingRepository<OvertimeRequestView, String>, QueryDslPredicateExecutor<OvertimeRequestView>{

}
