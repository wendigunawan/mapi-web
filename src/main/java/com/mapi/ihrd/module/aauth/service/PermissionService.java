package com.mapi.ihrd.module.aauth.service;

import com.mapi.ihrd.module.aauth.form.PermissionForm;
import com.mapi.ihrd.module.aauth.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PermissionService {

    Permission getById(String id);

    String add(PermissionForm form);

    void edit(PermissionForm form);

    Page<Permission> find(PageRequest pageRequest);


}
