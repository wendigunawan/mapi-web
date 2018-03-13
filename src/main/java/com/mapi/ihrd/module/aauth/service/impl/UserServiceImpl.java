package com.mapi.ihrd.module.aauth.service.impl;

import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.InvalidAccountException;
import com.mapi.ihrd.module.aauth.dao.UserDao;
import com.mapi.ihrd.module.aauth.dao.UserTokenDao;
import com.mapi.ihrd.module.aauth.dao.UserViewDao;
import com.mapi.ihrd.module.aauth.model.QUserView;
import com.mapi.ihrd.module.aauth.model.User;
import com.mapi.ihrd.module.aauth.model.UserToken;
import com.mapi.ihrd.module.aauth.model.UserView;
import com.mapi.ihrd.module.aauth.queryfilter.UserQueryFilter;
import com.mapi.ihrd.module.aauth.service.UserService;
import com.mapi.ihrd.module.employee.dao.EmployeeDao;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserViewDao userViewDao;

    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    @Override
    public UserView login(String username, String passwd) {

        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new InvalidAccountException("Invalid account");
        }

        if (!user.getPasswd().equals(passwd)) {
            throw new InvalidAccountException("Invalid account");
        }

        Collection<UserToken> tokens = userTokenDao.findByUserId(user.getId());

        UserToken token = null;

        if (tokens == null || tokens.isEmpty()) {
            //generate new token
            token = new UserToken();
            token.setExpiredDate(new Date());
            token.setUserId(user.getId());
            token.setToken(RandomStringUtils.randomAlphanumeric(13));

            userTokenDao.save(token);

        } else {

            if (tokens.iterator().hasNext()) {
                token = tokens.iterator().next();
            }
        }

        if (token == null) {
            throw new BusinessException("Gagal generate token");
        }

        UserView userView = userViewDao.findByUsername(username);
        userView.setToken(token.getToken());

        return userView;
    }

    @Override
    public Page<UserView> find(UserQueryFilter qf) {

        QUserView user = QUserView.userView;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(user.deleted.eq(false));

        if (StringUtils.hasLength(qf.getSearchText())) {
            builder.and(user.fullName.likeIgnoreCase(qf.getSearchText().toLowerCase()));
        }

        return userViewDao.findAll(builder, qf.getPageRequest());
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findByRefId(String refId) {
        return userDao.findByRefId(refId);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
