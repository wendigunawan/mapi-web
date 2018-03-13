package com.mapi.ihrd.module.aauth.dao;

import com.mapi.ihrd.module.aauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findByRefId(String refId);

}
