package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Bank;
import com.mapi.ihrd.module.employee.model.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface CertificateService {
    Iterable<Certificate> find();

    void save(Certificate certificate);

    Certificate getById(String id);

    Page<Certificate> find(PageRequest pageRequest);
}
