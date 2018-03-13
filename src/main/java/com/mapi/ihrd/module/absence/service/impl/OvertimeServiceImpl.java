package com.mapi.ihrd.module.absence.service.impl;

import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.absence.dao.OvertimeRequestDao;
import com.mapi.ihrd.module.absence.dao.OvertimeRequestViewDao;
import com.mapi.ihrd.module.absence.model.OvertimeRequest;
import com.mapi.ihrd.module.absence.model.OvertimeRequestView;
import com.mapi.ihrd.module.absence.model.QOvertimeRequestView;
import com.mapi.ihrd.module.absence.queryfilter.OvertimeQueryFilter;
import com.mapi.ihrd.module.absence.service.OvertimeService;
import com.mapi.ihrd.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;


@Service
@Transactional
public class OvertimeServiceImpl implements OvertimeService {

    @Autowired
    private OvertimeRequestDao overtimeRequestDao;

    @Autowired
    private OvertimeRequestViewDao overtimeRequestViewDao;

    @Override
    public void request(OvertimeRequest request) {

        request.setRequestDate(DateUtil.now());
        request.setDuration(1);//TODO perihitungan durasi
        request.setCreatedDate(DateUtil.now());
        request.setUpdatedDate(DateUtil.now());

        overtimeRequestDao.save(request);

    }

    @Override
    public void save(OvertimeRequest request) {
        request.setCreatedDate(DateUtil.now());
        request.setUpdatedDate(DateUtil.now());

        overtimeRequestDao.save(request);
    }

    @Override
    public OvertimeRequest getRequestById(String id) {
        return overtimeRequestDao.findOne(id);
    }

    @Override
    public Iterable<OvertimeRequest> findRequest() {
        return overtimeRequestDao.findAll();
    }

    @Override
    public void approve(String id, String note) {
        OvertimeRequest request = overtimeRequestDao.findOne(id);

        if (request == null) {
            throw new DataNotFoundException("Permohonan tidak ditemukan");
        }

        overtimeRequestDao.save(request);

    }

    @Override
    public void reject(String id, String note) {

        OvertimeRequest request = overtimeRequestDao.findOne(id);

        if (request == null) {
            throw new DataNotFoundException("Permohonan tidak ditemukan");
        }

        overtimeRequestDao.save(request);
    }

    @Override
    public Page<OvertimeRequest> find(PageRequest pageRequest) {
        return overtimeRequestDao.findAll(pageRequest);
    }

    @Override
    public Page<OvertimeRequestView> findAll(OvertimeQueryFilter filter) {
        QOvertimeRequestView requestView = QOvertimeRequestView.overtimeRequestView;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(requestView.deleted.eq(false));

        if (StringUtils.hasLength(filter.getSearchText())) {
            builder.and(requestView.fullName.likeIgnoreCase(filter.getSearchText().toLowerCase()));
        }

        return overtimeRequestViewDao.findAll(builder, filter.getPageRequest());
    }
}
