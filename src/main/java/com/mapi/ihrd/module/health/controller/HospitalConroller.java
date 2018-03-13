package com.mapi.ihrd.module.health.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.module.health.form.HospitalForm;
import com.mapi.ihrd.module.health.model.Hospital;
import com.mapi.ihrd.module.health.service.HospitalService;
import com.mapi.ihrd.response.JQueryDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/hospital")
public class HospitalConroller extends BaseController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public String index() {
        return "health/hospital.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datable(@RequestParam(required = false) Integer start,
                                   @RequestParam(required = false) Integer length,
                                   @RequestParam Integer draw) {

        PageRequest pageRequest = new PageRequest(start, length);
        Page<Hospital> hospitalPage = hospitalService.find(pageRequest);

        return new JQueryDataTable(hospitalPage.getContent(), hospitalPage.getTotalElements(), draw);

    }

    @GetMapping("/add")
    public String add(Model model) {
        HospitalForm form = new HospitalForm();
        model.addAttribute("hospital", form);
        return "health/hospital.form";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        Hospital dept = hospitalService.getById(id);

        HospitalForm form = new HospitalForm();
        form.setId(dept.getId());
        form.setName(dept.getName());
        form.setAddress(dept.getAddress());
        form.setPhoneNumber1(dept.getPhoneNumber1());
        form.setPhoneNumber2(dept.getPhoneNumber2());

        model.addAttribute("hospital", form);

        return "health/hospital.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute HospitalForm form) {

        if (StringUtils.hasLength(form.getId())) {
            hospitalService.edit(form);
        } else {
            hospitalService.add(form);
        }

        return ResponseEntity.ok().build();
    }


}
