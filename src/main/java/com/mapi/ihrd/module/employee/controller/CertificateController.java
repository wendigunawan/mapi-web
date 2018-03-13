package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Certificate;
import com.mapi.ihrd.module.employee.service.CertificateService;
import com.mapi.ihrd.response.JQueryDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/certificate")
public class CertificateController extends BaseController {
    @Autowired
    CertificateService certificateService;

    @GetMapping
    public String index() {
        return "employee/certificate/certificate.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(@RequestParam(required = false) Integer start,
                                     @RequestParam(required = false) Integer length,
                                     @RequestParam Integer draw) {

        PageRequest pageRequest = new PageRequest(start, length);
        Page<Certificate> page = certificateService.find(pageRequest);

        return new JQueryDataTable(page.getContent(), page.getTotalElements(), draw);

    }

    @GetMapping("/add")
    public String add(Model model) {
        Certificate certificate = new Certificate();
        model.addAttribute("certificate", certificate);
        return "employee/certificate/certificate.form";
    }

    @GetMapping("/edit/{id}")
    public String add(@PathVariable String id, Model model) {
        Certificate certificate = certificateService.getById(id);
        model.addAttribute("certificate", certificate);
        return "employee/certificate/certificate.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Certificate certificate, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/certificate/certificate.form";
        }

        certificateService.save(certificate);

        return "redirect:/web/certificate";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        Certificate certificate = certificateService.getById(id);

        if (certificate == null) {
            throw new DataNotFoundException("Bank tidak ditemukan");
        }

        certificate.setDeleted(true);
        certificateService.save(certificate);

        return "employee/certificate/certificate.list";
    }
}
