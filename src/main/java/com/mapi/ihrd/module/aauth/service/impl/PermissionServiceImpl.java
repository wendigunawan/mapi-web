package com.mapi.ihrd.module.aauth.service.impl;

import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.aauth.dao.PermissionDao;
import com.mapi.ihrd.module.aauth.form.PermissionForm;
import com.mapi.ihrd.module.aauth.model.Permission;
import com.mapi.ihrd.module.aauth.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private static final Logger LOG = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public Page<Permission> find(PageRequest pageRequest) {
        return permissionDao.findAll(pageRequest);
    }

    @Override
    public Permission getById(String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("id = " + id);
        }
        return permissionDao.findOne(id);
    }

    @Override
    public String add(PermissionForm form) {

        Permission permission = new Permission();
        BeanUtils.copyProperties(form, permission);

        permissionDao.save(permission);

        return permission.getId();
    }

    @Override
    public void edit(PermissionForm form) {

        if (!StringUtils.hasLength(form.getId())) {
            LOG.error("parameter permission id kosong");
            throw new DataNotFoundException("Izin Akses tidak terdaftar");
        }

        Permission permission = permissionDao.findOne(form.getId());

        if (permission == null) {
            LOG.error("permission id: " + form.getId() + " tidak terdaftar");
            throw new DataNotFoundException("Izin Akses tidak terdaftar");
        }

        boolean anyChanges = false;

        if (StringUtils.hasLength(form.getName())) {
            anyChanges = true;
            permission.setName(form.getName());
        }

        if (StringUtils.hasLength(form.getCode())) {
            anyChanges = true;
            permission.setCode(form.getCode());
        }

        if (StringUtils.hasLength(form.getDescription())) {
            anyChanges = true;
            permission.setDescription(form.getDescription());
        }

        if (!anyChanges) {
            LOG.error("tidak ada data yang akan diupdate");
            throw new BusinessException("Tidak ada perubahan terhadap data ini");
        }

        permissionDao.save(permission);

    }
}
