package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillDao extends PagingAndSortingRepository<Skill, String>, QueryDslPredicateExecutor<Skill> {

    Page<Skill> findByDeleted(boolean deleted, Pageable pageable);

}


