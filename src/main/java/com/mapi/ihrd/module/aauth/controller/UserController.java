package com.mapi.ihrd.module.aauth.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.module.aauth.model.UserView;
import com.mapi.ihrd.module.aauth.queryfilter.UserQueryFilter;
import com.mapi.ihrd.module.aauth.service.UserService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        return "aauth/user.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput param) {

        UserQueryFilter queryFilter = this.createFilter(param);

        Page<UserView> page = userService.find(queryFilter);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), param.getDraw());
    }

    private UserQueryFilter createFilter(JQueryDataTableInput param) {
        PageRequest pr = new PageRequest(param.getStart(), param.getLength());
        UserQueryFilter qf = new UserQueryFilter();
        qf.setPageRequest(pr);
        qf.setSearchText(param.getSearchText());
        return qf;
    }

}
