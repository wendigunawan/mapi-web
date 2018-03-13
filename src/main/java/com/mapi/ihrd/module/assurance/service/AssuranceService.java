package com.mapi.ihrd.module.assurance.service;

import com.mapi.ihrd.module.assurance.model.Assurance;
import com.mapi.ihrd.module.assurance.queryfilter.AssuranceQueryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface AssuranceService {
    Iterable<Assurance> find();

    void save(Assurance assurance);

    Assurance getById(String id);

    Page<Assurance> find(AssuranceQueryFilter queryFilter);
}
