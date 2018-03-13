package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.PayCheckDao;
import com.mapi.ihrd.module.employee.model.PayCheck;
import com.mapi.ihrd.module.employee.service.PayCheckService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class PayCheckServiceImpl implements PayCheckService {

    @Autowired
    PayCheckDao payCheckDao;

    @Override
    public void save(PayCheck payCheck) {

        payCheck.setCreatedDate(DateUtil.now());

        payCheckDao.save(payCheck);
    }

    @Override
    public PayCheck getById(String id) {
        return payCheckDao.findOne(id);
    }

    @Override
    public Collection<PayCheck> find() {
        return payCheckDao.findAll();
    }

    @Override
    public Page<PayCheck> find(int limit, int offset) {
        PageRequest pageRequest = new PageRequest(1, limit);

        Page<PayCheck> page = payCheckDao.findAll(pageRequest);

        return page;
    }

    @Override
    public Page<PayCheck> find(PageRequest pageRequest) {
        return payCheckDao.findAll(pageRequest);
    }
}
