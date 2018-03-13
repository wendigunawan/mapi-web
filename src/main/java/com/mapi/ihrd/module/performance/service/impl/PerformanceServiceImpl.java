package com.mapi.ihrd.module.performance.service.impl;

import com.mapi.ihrd.module.performance.dao.PerformanceDao;
import com.mapi.ihrd.module.performance.dao.PerformanceViewDao;
import com.mapi.ihrd.module.performance.model.Performance;
import com.mapi.ihrd.module.performance.model.PerformanceView;
import com.mapi.ihrd.module.performance.model.QPerformance;
import com.mapi.ihrd.module.performance.model.QPerformanceView;
import com.mapi.ihrd.module.performance.queryfilter.PerformanceQueryFilter;
import com.mapi.ihrd.module.performance.service.PerformanceService;
import com.mapi.ihrd.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    PerformanceDao performanceDao;

    @Autowired
    PerformanceViewDao performanceViewDao;

    @Override
    public void save(Performance performance) {
        performance.setCreatedDate(DateUtil.now());

        performanceDao.save(performance);
    }

    @Override
    public Iterable<Performance> findAll(PerformanceQueryFilter queryFilter) {

        QPerformance qPerformance = QPerformance.performance;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPerformance.deleted.eq(false));

        return performanceDao.findAll(builder);
    }

    @Override
    public Iterable<Performance> findByEmployee(String employeeId) {
        return null;
    }

    @Override
    public Page<Performance> find(PerformanceQueryFilter queryFilter) {
        return null;
    }

    @Override
    public Performance getById(String id) {

        QPerformance performance = QPerformance.performance;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(performance.deleted.eq(false).and(performance.id.eq(id)));

        return performanceDao.findOne(builder);
    }

    @Override
    public Page<PerformanceView> findView(PerformanceQueryFilter queryFilter) {

        QPerformanceView qPerformanceView = QPerformanceView.performanceView;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPerformanceView.deleted.eq(false));

        if(!queryFilter.getSearchText().isEmpty()){
            builder.and(qPerformanceView.fullName.likeIgnoreCase(queryFilter.getSearchText()));
        }

        return performanceViewDao.findAll(builder, queryFilter.getPageRequest());
    }

    @Override
    public Iterable<PerformanceView> findAllView(PerformanceQueryFilter queryFilter) {

        QPerformanceView qPerformanceView = QPerformanceView.performanceView;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPerformanceView.deleted.eq(false));

        if(!queryFilter.getSearchText().isEmpty()){
            builder.and(qPerformanceView.fullName.likeIgnoreCase(queryFilter.getSearchText()));
        }

        return performanceViewDao.findAll(builder);
    }

    @Override
    public Iterable<PerformanceView> findViewByEmployee(String employeeId) {

        QPerformanceView qPerformanceView = QPerformanceView.performanceView;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPerformanceView.deleted.eq(false)).and(qPerformanceView.nik.eq(employeeId));

        return performanceViewDao.findAll(builder);
    }
}
