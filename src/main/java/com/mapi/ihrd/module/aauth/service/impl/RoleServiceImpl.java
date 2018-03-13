package com.mapi.ihrd.module.aauth.service.impl;

import com.mapi.ihrd.exception.InvalidParameterValueException;
import com.mapi.ihrd.module.aauth.dao.RoleDao;
import com.mapi.ihrd.module.aauth.model.Role;
import com.mapi.ihrd.module.aauth.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.beans.Transient;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Override
    public Page<Role> find(PageRequest pageRequest) {
        return roleDao.findByDeleted(false, pageRequest);
    }

    @Override
    public Role getById(String id) {
        return roleDao.findOne(id);
    }

    @Override
    public void add(Role role) {
        roleDao.save(role);
    }

    @Override
    public void edit(Role role) {

        if (!StringUtils.hasText(role.getId())) {
            LOG.error("parameter role id kosong");
            throw new InvalidParameterValueException("Data tidak terdaftar");
        }

        roleDao.save(role);

    }

    @Transactional(readOnly = true)
    @Override
    public Role findByCode(String code) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("code = " + code);
        }
        return roleDao.findByCode(code);
    }
}
