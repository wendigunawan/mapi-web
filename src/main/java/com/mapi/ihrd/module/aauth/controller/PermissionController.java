package com.mapi.ihrd.module.aauth.controller;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.module.aauth.form.PermissionForm;
import com.mapi.ihrd.module.aauth.model.Permission;
import com.mapi.ihrd.module.aauth.service.PermissionService;
import com.mapi.ihrd.response.JQueryDataTable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String index() {
        return "aauth/permission.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datable(@RequestParam Integer draw) {

        PageRequest pageRequest = new PageRequest(0, Constant.DISPLAY_LENGTH);

        Page<Permission> rolePage = permissionService.find(pageRequest);

        JQueryDataTable jQueryDataTable = new JQueryDataTable(rolePage.getContent());

        jQueryDataTable.setDraw(draw);

        return jQueryDataTable;
    }

    @GetMapping("/add")
    public String add(Model model) {
        PermissionForm form = new PermissionForm();
        model.addAttribute("permission", form);
        return "aauth/permission.form";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        Permission permission = permissionService.getById(id);

        PermissionForm form = new PermissionForm();
        BeanUtils.copyProperties(permission, form);

        model.addAttribute("permission", form);

        return "aauth/permission.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute PermissionForm form) {


        if (StringUtils.hasLength(form.getId())) {
            permissionService.edit(form);
        } else {
            String id = permissionService.add(form);
            form.setId(id);
        }


        return ResponseEntity.ok(form);
    }

}
