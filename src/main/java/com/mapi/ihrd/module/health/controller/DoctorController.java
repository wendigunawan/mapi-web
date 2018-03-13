package com.mapi.ihrd.module.health.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.module.health.form.DoctorForm;
import com.mapi.ihrd.module.health.model.Doctor;
import com.mapi.ihrd.module.health.model.Hospital;
import com.mapi.ihrd.module.health.service.DoctorService;
import com.mapi.ihrd.module.health.service.HospitalService;
import com.mapi.ihrd.response.JQueryDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/web/doctor")
public class DoctorController extends BaseController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public String index() {
        return "health/doctor.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datable(@RequestParam(required = false) Integer start,
                                   @RequestParam(required = false) Integer length,
                                   @RequestParam Integer draw) {

        PageRequest pageRequest = new PageRequest(start, length);
        Page<Doctor> doctorPage = doctorService.find(pageRequest);

        return new JQueryDataTable(doctorPage.getContent(), doctorPage.getTotalElements(), draw);
    }


    @ModelAttribute("hospitals")
    public Collection<Hospital> getHospitals() {
        return hospitalService.findAll();

    }

    @GetMapping("/add")
    public String add(Model model) {
        DoctorForm form = new DoctorForm();
        model.addAttribute("doctor", form);
        return "health/doctor.form";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        Doctor doctor = doctorService.getById(id);

        DoctorForm form = new DoctorForm();
        form.setId(doctor.getId());
        form.setFullName(doctor.getFullName());
        form.setSpecialist(doctor.getSpecialist());
        form.setHospitalId(doctor.getHospital().getId());

        model.addAttribute("doctor", form);

        return "health/doctor.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DoctorForm form) {

        if (StringUtils.hasLength(form.getId())) {
            doctorService.edit(form);
        } else {
            doctorService.add(form);
        }

        return "redirect:/web/news";
    }
}
