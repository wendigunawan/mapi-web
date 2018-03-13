package com.mapi.ihrd.module.aauth.service.impl;

import com.mapi.ihrd.model.UserLogin;
import com.mapi.ihrd.module.aauth.dao.UserDao;
import com.mapi.ihrd.module.aauth.dao.UserViewDao;
import com.mapi.ihrd.module.aauth.model.Role;
import com.mapi.ihrd.module.aauth.model.User;
import com.mapi.ihrd.module.aauth.model.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserViewDao userViewDao;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserView userView = userViewDao.findByUsername(username);
        if (userView == null) {
            LOG.error("username: " + username + " tidak terdaftar");
            throw new UsernameNotFoundException("Invalid account");
        }

        User user = userDao.findOne(userView.getId());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new UserLogin(userView, grantedAuthorities);

        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswd(), grantedAuthorities);
    }
}
