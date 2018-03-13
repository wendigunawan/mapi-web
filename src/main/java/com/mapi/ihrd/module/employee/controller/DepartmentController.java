package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Department;
import com.mapi.ihrd.module.employee.service.DepartmentService;
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
@RequestMapping("/web/department")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String index() {
        return "employee/department/department.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(@RequestParam(required = false) Integer start,
                                     @RequestParam(required = false) Integer length,
                                     @RequestParam Integer draw) {

        PageRequest pageRequest = new PageRequest(start, length);
        Page<Department> page = departmentService.find(pageRequest);

        return new JQueryDataTable(page.getContent(), page.getTotalElements(), draw);

    }

    @GetMapping("/add")
    public String add(Model model) {
        Department dept = new Department();
        model.addAttribute("department", dept);
        return "employee/department/department.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Department dept = departmentService.getById(id);
        model.addAttribute("department", dept);
        return "employee/department/department.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Department dept, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/department/department.form";
        }

        departmentService.save(dept);

        return "redirect:/web/department";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        Department department = departmentService.getById(id);

        if (department == null) {
            throw new DataNotFoundException("Department tidak ditemukan");
        }

        department.setDeleted(true);
        departmentService.save(department);

        return "employee/department/department.list";
    }
}
