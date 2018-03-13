package com.mapi.ihrd.module.absence.service.impl;

import com.mapi.ihrd.module.absence.dao.LateRequestDao;
import com.mapi.ihrd.module.absence.model.LateRequest;
import com.mapi.ihrd.module.absence.service.LateService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LateServiceImpl implements LateService {

    @Autowired
    private LateRequestDao lateRequestDao;

    @Override
    public void request(LateRequest request) {

        request.setRequestDate(DateUtil.now());
        request.setCreatedDate(DateUtil.now());
        request.setUpdatedDate(DateUtil.now());
        request.setStatus(LateRequest.Status.NEW);

        lateRequestDao.save(request);

    }

    @Transactional(readOnly = true)
    @Override
    public Page<LateRequest> find(PageRequest pageRequest) {
        return lateRequestDao.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public LateRequest getRequestById(String id) {
        return lateRequestDao.findOne(id);
    }
}
