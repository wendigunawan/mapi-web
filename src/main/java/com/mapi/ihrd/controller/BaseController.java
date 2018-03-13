package com.mapi.ihrd.controller;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

public class BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    public PageRequest createPageRequest(Integer offset, Integer limit, Sort sort) {
        if (offset == null || offset.intValue() < 0) {
            offset = 0;
        }

        if (limit == null) {
            limit = Constant.DISPLAY_LENGTH;
        }

        int page = 0;
        if (offset > 0) {
            page = Double.valueOf(offset / limit).intValue();
        }

        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, "createdDate");
        }

        return new PageRequest(page, limit, sort);
    }

    public PageRequest createPageRequest(Integer offset, Integer limit) {
        return createPageRequest(offset, limit, null);
    }

    public PageRequest createPageRequest(Integer offset) {
        return createPageRequest(offset, Constant.DISPLAY_LENGTH);
    }

    @ModelAttribute("userLogin")
    public UserLogin getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null) {
            return (UserLogin) principal;
        }
        return null;
    }
}
