package com.mapi.ihrd.module.aauth.controller;

import com.mapi.ihrd.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/setting")
public class SettingController extends BaseController {

    @GetMapping
    public String index() {
        return "aauth/setting.list";
    }
}
