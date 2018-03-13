package com.mapi.ihrd.module.aauth.service;

import com.mapi.ihrd.module.aauth.model.User;
import com.mapi.ihrd.module.aauth.model.UserView;
import com.mapi.ihrd.module.aauth.queryfilter.UserQueryFilter;
import org.springframework.data.domain.Page;

public interface UserService {

    UserView login(String username, String passwd);

    Page<UserView> find(UserQueryFilter filter);

    void save(User user);

    User findByRefId(String refId);

    User findByUsername(String username);
}
