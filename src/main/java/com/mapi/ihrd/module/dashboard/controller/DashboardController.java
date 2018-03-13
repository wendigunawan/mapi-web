package com.mapi.ihrd.module.dashboard.controller;

import com.mapi.ihrd.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController extends BaseController {

    @GetMapping(value = {"/", "/web/dashboard"})
    public String index() {
        System.out.println("#1");
        System.out.println(getCurrentUser().getUsername());
        System.out.println(getCurrentUser().getDetail().getFullName());
        System.out.println("#2");

        //return "dashboard/dashboard";
        return "registration";
    }
}
