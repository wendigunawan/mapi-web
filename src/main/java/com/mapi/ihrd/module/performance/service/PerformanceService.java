package com.mapi.ihrd.module.performance.service;

import com.mapi.ihrd.module.performance.model.Performance;
import com.mapi.ihrd.module.performance.model.PerformanceView;
import com.mapi.ihrd.module.performance.queryfilter.PerformanceQueryFilter;
import org.springframework.data.domain.Page;

public interface PerformanceService {

    void save(Performance performance);

    Iterable<Performance> findAll(PerformanceQueryFilter queryFilter);

    Iterable<Performance> findByEmployee(String employeeId);

    Page<Performance> find(PerformanceQueryFilter queryFilter);

    Performance getById(String id);

    Page<PerformanceView> findView(PerformanceQueryFilter queryFilter);

    Iterable<PerformanceView> findAllView(PerformanceQueryFilter queryFilter);

    Iterable<PerformanceView> findViewByEmployee(String employeeId);
}
