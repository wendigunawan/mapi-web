package com.mapi.ihrd.model;

import com.mapi.ihrd.module.aauth.model.UserView;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.User;


import java.util.Set;

public class UserLogin extends User {

    private UserView user;

    public UserLogin(UserView user, Set<GrantedAuthority> grantedAuthorities) {
        super(user.getUsername(), user.getPasswd(), grantedAuthorities);
        this.user = user;
    }

    public UserView getDetail() {
        return user;
    }

}

