package com.mapi.ihrd.module.leave.dao;

import com.mapi.ihrd.module.leave.model.LeaveRequest;
import com.mapi.ihrd.module.leave.model.LeaveRequestView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestViewDao extends PagingAndSortingRepository<LeaveRequestView, String>, QueryDslPredicateExecutor<LeaveRequestView> {

    Page<LeaveRequestView> findByStatus(LeaveRequest.Status status, Pageable pageable);

    Page<LeaveRequestView> findByType(LeaveRequest.Type type, Pageable pageable);

    Page<LeaveRequestView> findByTypeAndStatus(LeaveRequest.Type type, LeaveRequest.Status status, Pageable pageable);


}
