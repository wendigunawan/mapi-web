package com.mapi.ihrd.module.absence.service;

import com.mapi.ihrd.module.absence.model.LateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface LateService {

    void request(LateRequest request);

    Page<LateRequest> find(PageRequest pageRequest);

    LateRequest getRequestById(String id);
}
