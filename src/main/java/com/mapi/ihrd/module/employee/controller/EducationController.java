package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Education;
import com.mapi.ihrd.module.employee.model.Religion;
import com.mapi.ihrd.module.employee.service.EducationService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/education")
public class EducationController extends BaseController {

    @Autowired
    private EducationService educationService;

    @GetMapping
    public String index() {
        return "employee/education/education.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable<Education> dataTable(JQueryDataTableInput input) {

        PageRequest pageRequest = createPageRequest(input.getStart(), input.getLength());
        Page<Education> page = educationService.find(pageRequest);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("education", new Education());
        return "employee/education/education.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Education education = educationService.getById(id);
        model.addAttribute("education", education);
        return "employee/education/education.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Education education, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/education/education.form";
        }

        educationService.save(education);

        return "redirect:/web/education";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Education education = educationService.getById(id);

        if (education == null) {
            throw new DataNotFoundException("Pendidikan tidak ditemukan");
        }

        education.setDeleted(true);
        educationService.save(education);

        return ResponseEntity.ok().build();
    }
}
