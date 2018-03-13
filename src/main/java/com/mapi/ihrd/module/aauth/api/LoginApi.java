package com.mapi.ihrd.module.aauth.api;


import com.mapi.ihrd.module.aauth.form.LoginForm;
import com.mapi.ihrd.module.aauth.model.UserView;
import com.mapi.ihrd.module.aauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApi {

    @Autowired
    private UserService userService;

    @PostMapping("/api/account/login")
    public ResponseEntity index(@RequestBody LoginForm form) {

        UserView user = userService.login(form.getUsername(), form.getPasswd());

        return ResponseEntity.ok(user);
    }


}
