package com.mapi.ihrd.module.aauth.service;

import com.mapi.ihrd.module.aauth.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RoleService {

    Page<Role> find(PageRequest pageRequest);

    //TODO ganti nama method dengan findById
    Role getById(String id);

    void add(Role role);

    void edit(Role role);

    Role findByCode(String code);
}

