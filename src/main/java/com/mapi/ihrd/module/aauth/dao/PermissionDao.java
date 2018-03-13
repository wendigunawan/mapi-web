package com.mapi.ihrd.module.aauth.dao;

import com.mapi.ihrd.module.aauth.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends JpaRepository<Permission, String> {
}
