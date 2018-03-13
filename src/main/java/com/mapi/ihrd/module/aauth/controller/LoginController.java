package com.mapi.ihrd.module.aauth.controller;


import com.mapi.ihrd.module.aauth.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping({"/web/aauth/login", "/login"})
    public String index(Model model) {


        model.addAttribute("user", new LoginForm());

        return "aauth/login";
    }
}
