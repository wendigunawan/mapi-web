package com.mapi.ihrd.module.health.service;

import com.mapi.ihrd.module.health.form.HospitalForm;
import com.mapi.ihrd.module.health.model.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface HospitalService {

    Hospital getById(String id);

    String add(HospitalForm form);

    void edit(HospitalForm form);

    Page<Hospital> find(PageRequest pageRequest);

    Collection<Hospital> findAll();
}
