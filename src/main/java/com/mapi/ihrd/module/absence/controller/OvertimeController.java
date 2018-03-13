package com.mapi.ihrd.module.absence.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.aauth.queryfilter.UserQueryFilter;
import com.mapi.ihrd.module.absence.model.*;
import com.mapi.ihrd.module.absence.queryfilter.OvertimeQueryFilter;
import com.mapi.ihrd.module.absence.service.OvertimeService;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.module.leave.form.LeaveForm;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import com.mapi.ihrd.util.DateUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/web/overtime")
public class OvertimeController extends BaseController {

    @Autowired
    OvertimeService overtimeService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String index() {
        return "absence/overtime/overtime.list";
    }

    @ModelAttribute("employees")
    public Collection<Employee> getEmployee(){
        return employeeService.find();
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        OvertimeQueryFilter filter = new OvertimeQueryFilter();
        PageRequest pr = createPageRequest(input.getStart(), input.getLength());
        filter.setPageRequest(pr);
        filter.setSearchText(input.getSearchText());

        Page<OvertimeRequestView> page = overtimeService.findAll(filter);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        OvertimeRequest overtimeRequest = new OvertimeRequest();
        overtimeRequest.setRequestDate(DateUtil.now());
        model.addAttribute("overtime", overtimeRequest);
        return "absence/overtime/overtime.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid OvertimeRequest overtime, BindingResult result) {

        if (result.hasErrors()) {
            return "absence/overtime/overtime.form";
        }

        overtime.setStatus(OvertimeRequestBaseModel.Status.NEW);
        overtimeService.save(overtime);

        return "redirect:/web/overtime";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        OvertimeRequest overtimeRequest = overtimeService.getRequestById(id);

        if (overtimeRequest == null) {
            throw new DataNotFoundException("Lembur tidak ditemukan");
        }

        overtimeRequest.setDeleted(true);
        overtimeService.save(overtimeRequest);

        return ResponseEntity.ok().build();
    }
}
