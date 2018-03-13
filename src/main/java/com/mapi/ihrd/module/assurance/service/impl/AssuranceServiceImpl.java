package com.mapi.ihrd.module.assurance.service.impl;

import com.mapi.ihrd.module.assurance.dao.AssuranceDao;
import com.mapi.ihrd.module.assurance.model.Assurance;
import com.mapi.ihrd.module.assurance.model.QAssurance;
import com.mapi.ihrd.module.assurance.queryfilter.AssuranceQueryFilter;
import com.mapi.ihrd.module.assurance.service.AssuranceService;
import com.mapi.ihrd.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
@Transactional
public class AssuranceServiceImpl implements AssuranceService {

    @Autowired
    AssuranceDao assuranceDao;

    @Override
    public Iterable<Assurance> find() {
        return assuranceDao.findAll();
    }

    @Override
    public void save(Assurance assurance) {
        assurance.setCreatedDate(DateUtil.now());
        assuranceDao.save(assurance);
    }

    @Override
    public Assurance getById(String id) {
        return assuranceDao.findOne(id);
    }

    @Override
    public Page<Assurance> find(AssuranceQueryFilter queryFilter) {
        QAssurance assurance = QAssurance.assurance;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(assurance.deleted.eq(false));

        if (StringUtils.hasLength(queryFilter.getSearchText())) {
            builder.and(assurance.name.likeIgnoreCase(queryFilter.getSearchText().toLowerCase()));
        }


        return assuranceDao.findAll(builder, queryFilter.getPageRequest());
    }
}
