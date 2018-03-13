package com.mapi.ihrd.module.performance.service.impl;

import com.mapi.ihrd.module.performance.dao.ComponentDao;
import com.mapi.ihrd.module.performance.model.Component;
import com.mapi.ihrd.module.performance.model.QComponent;
import com.mapi.ihrd.module.performance.queryfilter.ComponentQueryFilter;
import com.mapi.ihrd.module.performance.service.ComponentService;
import com.mapi.ihrd.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@Transactional
@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    ComponentDao componentDao;

    @Override
    public void save(Component component) {
        component.setCreatedDate(DateUtil.now());
        componentDao.save(component);
    }

    @Override
    public Iterable<Component> findAll() {

        QComponent qComponent = QComponent.component;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qComponent.deleted.eq(false));

        return componentDao.findAll(builder);
    }

    @Override
    public Page<Component> findAll(ComponentQueryFilter queryFilter) {

        QComponent qComponent = QComponent.component;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qComponent.deleted.eq(false));

        if(!queryFilter.getSearchText().isEmpty()){
            builder.and(qComponent.name.likeIgnoreCase(queryFilter.getSearchText()));
        }

        return componentDao.findAll(builder, queryFilter.getPageRequest());
    }

    @Override
    public Component getById(String id) {
        QComponent qComponent = QComponent.component;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qComponent.deleted.eq(false)).and(qComponent.id.eq(id));

        return componentDao.findOne(builder);
    }
}
