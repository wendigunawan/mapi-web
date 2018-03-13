package com.mapi.ihrd.module.aauth.dao;

import com.mapi.ihrd.module.aauth.model.UserView;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserViewDao extends PagingAndSortingRepository<UserView, String>, QueryDslPredicateExecutor<UserView> {
    UserView findByUsername(String username);
}
