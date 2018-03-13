package com.mapi.ihrd.module.aauth.dao;

import com.mapi.ihrd.module.aauth.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface UserTokenDao extends JpaRepository<UserToken, String> {

    Collection<UserToken> findByUserId(String userId);

}
