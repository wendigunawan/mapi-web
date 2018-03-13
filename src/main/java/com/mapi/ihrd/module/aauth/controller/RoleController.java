package com.mapi.ihrd.module.aauth.controller;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.aauth.model.Role;
import com.mapi.ihrd.module.aauth.service.RoleService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index() {
        return "aauth/role.list";
    }


    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datable(JQueryDataTableInput input) {

        PageRequest pageRequest = new PageRequest(input.getStart(), input.getLength());
        Page<Role> rolePage = roleService.find(pageRequest);

        return new JQueryDataTable<>(rolePage.getContent(), rolePage.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "aauth/role.form";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "aauth/role.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute Role role) {
        if (StringUtils.hasText(role.getId())) {
            roleService.edit(role);
        } else {
            roleService.add(role);
        }
        return ResponseEntity.ok(role);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity save(@PathVariable String id) {

        Role role = roleService.getById(id);
        if (role == null) {
            throw new DataNotFoundException("Peran tidak ditemukan");
        }

        role.setDeleted(true);
        roleService.edit(role);

        return ResponseEntity.ok().build();
    }

}
