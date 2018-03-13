package com.mapi.ihrd.module.aauth.dao;

import com.mapi.ihrd.module.aauth.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {

    Page<Role> findByDeleted(boolean deleted, Pageable pageable);

    Role findByCode(String code);
}
