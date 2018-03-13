package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.form.PayCheckForm;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.model.PayCheck;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.module.employee.service.PayCheckService;
import com.mapi.ihrd.response.JQueryDataTable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("web/paycheck")
public class PayCheckController extends BaseController{

    @Autowired
    PayCheckService payCheckService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String index() {
        return "employee/paycheck/paycheck.list";
    }

    @Value("${app.path.slipgaji}")
    private String slipGajiPath;

    @ModelAttribute("employees")
    public Collection<Employee> getEmployee(){
        return employeeService.find();
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datatable(@RequestParam(required = false) Integer start,
                                     @RequestParam(required = false) Integer length,
                                     @RequestParam Integer draw) {

        PageRequest pageRequest = createPageRequest(start, length);

        Page<PayCheck> employeePage = payCheckService.find(pageRequest);

        return new JQueryDataTable(employeePage.getContent(), employeePage.getTotalElements(), draw);
    }

    @GetMapping("/add")
    public String add(Model model) {
        PayCheckForm form = new PayCheckForm();
        model.addAttribute("paycheck", form);
        return "employee/paycheck/paycheck.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        PayCheck payCheck = payCheckService.getById(id);

        PayCheckForm form = new PayCheckForm();

        BeanUtils.copyProperties(payCheck, form);

        model.addAttribute("paycheck", form);

        return "employee/paycheck/paycheck.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute PayCheckForm form) {

        PayCheck payCheck = new PayCheck();
        payCheck.setEmployee(form.getEmployee());
        payCheck.setSalaryDate(form.getSalaryDate());

        File f = new File(slipGajiPath + "/" + form.getEmployee().getNik());
        if (!f.exists() || (f.exists() && !f.isDirectory())) {
            if (!f.mkdir()) {
                throw new BusinessException("Gagal membuat directory");
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(f.getAbsolutePath() + "/" + form.getFile().getOriginalFilename());
            FileCopyUtils.copy(form.getFile().getInputStream(), fos);
            payCheck.setFileName(form.getFile().getOriginalFilename());
            payCheck.setFileNameOrigin(f.getAbsolutePath() + "/" + form.getFile().getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

        payCheckService.save(payCheck);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        PayCheck payCheck = payCheckService.getById(id);

        if (payCheck == null) {
            throw new DataNotFoundException("Gaji tidak ditemukan");
        }

        payCheck.setDeleted(true);
        payCheckService.save(payCheck);

        return "employee/paycheck/paycheck.list";
    }
}
