package com.mapi.ihrd.module.absence.service;

import com.mapi.ihrd.module.absence.model.OvertimeRequest;
import com.mapi.ihrd.module.absence.model.OvertimeRequestView;
import com.mapi.ihrd.module.absence.queryfilter.OvertimeQueryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface OvertimeService {

    void request(OvertimeRequest request);

    void save(OvertimeRequest overtimeRequest);

    OvertimeRequest getRequestById(String id);

    Iterable<OvertimeRequest> findRequest();

    void approve(String id, String note);

    void reject(String id, String note);

    Page<OvertimeRequest> find(PageRequest pageRequest);

    Page<OvertimeRequestView> findAll(OvertimeQueryFilter filter);
}
