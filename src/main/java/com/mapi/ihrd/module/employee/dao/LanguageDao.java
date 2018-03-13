package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Language;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LanguageDao extends PagingAndSortingRepository<Language, String>, QueryDslPredicateExecutor<Language>{
}
