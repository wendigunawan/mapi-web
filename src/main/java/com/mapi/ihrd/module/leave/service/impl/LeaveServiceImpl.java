package com.mapi.ihrd.module.leave.service.impl;

import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.absence.queryfilter.AbsenceQueryFilter;
import com.mapi.ihrd.module.leave.dao.LeaveDao;
import com.mapi.ihrd.module.leave.dao.LeaveRequestDao;
import com.mapi.ihrd.module.leave.dao.LeaveRequestViewDao;
import com.mapi.ihrd.module.leave.form.LeaveRequestForm;
import com.mapi.ihrd.module.leave.model.Leave;
import com.mapi.ihrd.module.leave.model.LeaveRequest;
import com.mapi.ihrd.module.leave.model.LeaveRequestView;
import com.mapi.ihrd.module.leave.model.QLeaveRequestView;
import com.mapi.ihrd.module.leave.queryfilter.LeaveRequestQueryFilter;
import com.mapi.ihrd.module.leave.service.LeaveService;
import com.mapi.ihrd.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Transactional
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRequestDao leaveRequestDao;

    @Autowired
    private LeaveRequestViewDao leaveRequestViewDao;

    @Autowired
    private LeaveDao leaveDao;

    @Override
    public void request(LeaveRequestForm form) {
        
    }

    @Override
    public void request(LeaveRequest request) {

        request.setStatus(LeaveRequest.Status.NEW);
        request.setRequestDate(DateUtil.now());

        leaveRequestDao.save(request);

    }

    @Override
    public void approve(String requestId, String note) {

        LeaveRequest request = leaveRequestDao.findOne(requestId);
        if (request == null) {
            throw new DataNotFoundException("Permohonan tidak ditemukan");
        }

        if (!LeaveRequest.Status.NEW.equals(request.getStatus())) {
            throw new BusinessException("Permohonan ini sudah diproses");
        }


        request.setStatus(LeaveRequest.Status.APPROVED);
        if (StringUtils.hasLength(note)) {
            request.setProcessNote(note);
        }

        leaveRequestDao.save(request);
    }

    @Override
    public void reject(String requestId, String note) {
        LeaveRequest request = leaveRequestDao.findOne(requestId);
        if (request == null) {
            throw new DataNotFoundException("Permohonan tidak ditemukan");
        }

        if (!LeaveRequest.Status.NEW.equals(request.getStatus())) {
            throw new BusinessException("Permohonan ini sudah diproses");
        }

        request.setStatus(LeaveRequest.Status.REJECTED);
        if (StringUtils.hasLength(note)) {
            request.setProcessNote(note);
        }

        leaveRequestDao.save(request);
    }

    @Override
    public Page<LeaveRequestView> findRequest(Pageable pageable) {
        return leaveRequestViewDao.findAll(pageable);
    }

    @Override
    public Page<LeaveRequestView> findRequest(LeaveRequest.Status status, Pageable pageable) {
        return leaveRequestViewDao.findByStatus(status, pageable);
    }

    @Override
    public Page<LeaveRequestView> findRequest(LeaveRequest.Type type, Pageable pageable) {
        return leaveRequestViewDao.findByType(type, pageable);
    }

    @Override
    public Page<LeaveRequestView> findRequest(LeaveRequestQueryFilter queryFilter) {
        QLeaveRequestView requestView = QLeaveRequestView.leaveRequestView;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(requestView.deleted.eq(false)).and(requestView.status.eq(LeaveRequest.Status.NEW));

        if (StringUtils.hasLength(queryFilter.getSearchText())) {
            builder.and(requestView.fullName.likeIgnoreCase(queryFilter.getSearchText().toLowerCase()));
        }

        return leaveRequestViewDao.findAll(builder, queryFilter.getPageRequest());
    }

    @Override
    public Page<LeaveRequestView> findHistoryRequest(LeaveRequestQueryFilter queryFilter) {
        QLeaveRequestView requestView = QLeaveRequestView.leaveRequestView;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(requestView.deleted.eq(false).and(requestView.status.ne(LeaveRequest.Status.NEW)));

        if (StringUtils.hasLength(queryFilter.getSearchText())) {
            builder.and(requestView.fullName.likeIgnoreCase(queryFilter.getSearchText().toLowerCase()));
        }

        return leaveRequestViewDao.findAll(builder, queryFilter.getPageRequest());
    }

    @Override
    public Page<LeaveRequestView> findRequest(LeaveRequest.Type type, LeaveRequest.Status status, Pageable pageable) {
        return leaveRequestViewDao.findByTypeAndStatus(type, status, pageable);
    }

    @Override
    public LeaveRequest getRequestById(String id) {
        return leaveRequestDao.findOne(id);
    }

    @Override
    public void save(Leave leave) {
        leave.setCreatedDate(DateUtil.now());
        leaveDao.save(leave);
    }

    @Override
    public Page<Leave> find(Pageable pageable) {
        return leaveDao.findAll(pageable);
    }

    @Override
    public Leave getById(String id) {
        return leaveDao.findOne(id);
    }

    @Override
    public Collection<Leave> find() {
        return leaveDao.findAll();
    }
}
