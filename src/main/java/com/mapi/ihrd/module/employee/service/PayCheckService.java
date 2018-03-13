package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.PayCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface PayCheckService {
    void save(PayCheck payCheck);

    PayCheck getById(String id);

    Collection<PayCheck> find();

    Page<PayCheck> find(int limit, int offset);

    Page<PayCheck> find(PageRequest pageRequest);
}
