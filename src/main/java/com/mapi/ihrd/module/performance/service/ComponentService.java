package com.mapi.ihrd.module.performance.service;

import com.mapi.ihrd.module.performance.model.Component;
import com.mapi.ihrd.module.performance.queryfilter.ComponentQueryFilter;
import org.springframework.data.domain.Page;

import java.util.Iterator;

public interface ComponentService {

    void save(Component component);

    Iterable<Component> findAll();

    Page<Component> findAll(ComponentQueryFilter queryFilter);

    Component getById(String id);
}
