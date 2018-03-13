package com.mapi.ihrd.module.leave.service;

import com.mapi.ihrd.module.leave.form.LeaveRequestForm;
import com.mapi.ihrd.module.absence.queryfilter.AbsenceQueryFilter;
import com.mapi.ihrd.module.leave.model.Leave;
import com.mapi.ihrd.module.leave.model.LeaveRequest;
import com.mapi.ihrd.module.leave.model.LeaveRequestView;
import com.mapi.ihrd.module.leave.queryfilter.LeaveRequestQueryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface LeaveService {

    void request(LeaveRequestForm form);

    void request(LeaveRequest request);

    void approve(String requestId, String note);

    void reject(String requestId, String note);

    Page<LeaveRequestView> findRequest(Pageable pageable);

    Page<LeaveRequestView> findRequest(LeaveRequest.Status status, Pageable pageable);

    Page<LeaveRequestView> findRequest(LeaveRequest.Type type, Pageable pageable);

    Page<LeaveRequestView> findRequest(LeaveRequestQueryFilter queryFilter);

    Page<LeaveRequestView> findHistoryRequest(LeaveRequestQueryFilter queryFilter);

    Page<LeaveRequestView> findRequest(LeaveRequest.Type type, LeaveRequest.Status status, Pageable pageable);

    LeaveRequest getRequestById(String id);

    void save(Leave leave);

    Page<Leave> find(Pageable pageable);

    Leave getById(String id);

    Collection<Leave> find();
}
