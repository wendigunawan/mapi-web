package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Bank;
import com.mapi.ihrd.module.employee.model.EmploymentStatus;
import com.mapi.ihrd.module.employee.service.BankService;
import com.mapi.ihrd.module.employee.service.EmploymentStatusService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/employment")
public class EmploymentStatusController extends BaseController {

    @Autowired
    EmploymentStatusService employmentStatusService;

    @GetMapping
    public String index() {
        return "employee/employment/employment.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        PageRequest pageRequest = new PageRequest(input.getStart(), input.getLength());
        Page<EmploymentStatus> page = employmentStatusService.find(pageRequest);

        return new JQueryDataTable(page.getContent(), page.getTotalElements(), input.getDraw());

    }

    @GetMapping("/add")
    public String add(Model model) {
        EmploymentStatus employmentStatus = new EmploymentStatus();
        model.addAttribute("employment", employmentStatus);
        return "employee/employment/employment.form";
    }

    @GetMapping("/edit/{id}")
    public String add(@PathVariable String id, Model model) {
        EmploymentStatus employmentStatus = employmentStatusService.getById(id);
        model.addAttribute("employment", employmentStatus);
        return "employee/employment/employment.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid EmploymentStatus employmentStatus, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/employment/employment.form";
        }

        employmentStatusService.save(employmentStatus);

        return "redirect:/web/employment";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        EmploymentStatus employmentStatus = employmentStatusService.getById(id);

        if (employmentStatus == null) {
            throw new DataNotFoundException("Employment tidak ditemukan");
        }

        employmentStatus.setDeleted(true);
        employmentStatusService.save(employmentStatus);

        return "employee/employment/employment.list";
    }
}
