package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.CertificateDao;
import com.mapi.ihrd.module.employee.model.Certificate;
import com.mapi.ihrd.module.employee.service.CertificateService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    CertificateDao certificateDao;

    @Override
    public Iterable<Certificate> find() {
        return certificateDao.findAll();
    }

    @Override
    public void save(Certificate certificate) {
        certificate.setCreatedDate(DateUtil.now());
        certificateDao.save(certificate);
    }

    @Override
    public Certificate getById(String id) {
        return certificateDao.findOne(id);
    }

    @Override
    public Page<Certificate> find(PageRequest pageRequest) {
        return certificateDao.findAll(pageRequest);
    }
}
