package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Religion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ReligionService {

    Page<Religion> find(PageRequest pageRequest);

    Page<Religion> find(PageRequest pageRequest, boolean deleted);

    void save(Religion religion);

    Religion getById(String id);

}
