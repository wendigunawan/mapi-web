package com.mapi.ihrd.module.leave.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.module.leave.form.LeaveRequestForm;
import com.mapi.ihrd.module.leave.model.LeaveRequest;
import com.mapi.ihrd.module.leave.model.LeaveRequestView;
import com.mapi.ihrd.module.leave.queryfilter.LeaveRequestQueryFilter;
import com.mapi.ihrd.module.leave.service.LeaveService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/web/leaverequest")
public class LeaveRequestController extends BaseController{

    @Autowired
    LeaveService leaveService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String index() {
        return "leave/leaverequest/leaverequest.list";
    }

    @GetMapping("history")
    public String history(){
        return "leave/historyrequest/historyrequest.list";
    }

    @ModelAttribute("employees")
    public Collection<Employee> getEmployee(){
        return employeeService.find();
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        LeaveRequestQueryFilter filter = new LeaveRequestQueryFilter();
        PageRequest pageRequest = createPageRequest(input.getStart(), input.getLength());
        filter.setSearchText(input.getSearchText());
        filter.setPageRequest(pageRequest);

        Page<LeaveRequestView> page = leaveService.findRequest(filter);

        return new JQueryDataTable(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @PostMapping("/historydata")
    @ResponseBody
    public JQueryDataTable history(JQueryDataTableInput input) {

        LeaveRequestQueryFilter filter = new LeaveRequestQueryFilter();
        PageRequest pageRequest = createPageRequest(input.getStart(), input.getLength());
        filter.setSearchText(input.getSearchText());
        filter.setPageRequest(pageRequest);

        Page<LeaveRequestView> page = leaveService.findHistoryRequest(filter);

        return new JQueryDataTable(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        LeaveRequestForm leaveForm = new LeaveRequestForm();
        leaveForm.setStartDate(DateUtil.now());
        leaveForm.setEndDate(DateUtil.now());
        model.addAttribute("leaverequest", leaveForm);
        model.addAttribute("typeLeave", LeaveRequest.Type.LEAVE);
        model.addAttribute("typeSick", LeaveRequest.Type.SICK);
        model.addAttribute("typePermission", LeaveRequest.Type.PERMISSION);
        model.addAttribute("typeLate", LeaveRequest.Type.LATE);
        return "leave/leaverequest/leaverequest.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute LeaveRequestForm leaveForm, BindingResult result) {

        if (result.hasErrors()) {
            throw new BusinessException("Error");
        }

        LeaveRequest leave = new LeaveRequest();

        leave.setRequestDate(DateUtil.now());
        leave.setRequestBy(leaveForm.getEmployeeId());
        leave.setStartDate(leaveForm.getStartDate());
        leave.setEndDate(leaveForm.getEndDate());
        leave.setDuration(leaveForm.getDuration());
        leave.setNote(leaveForm.getNote());
        leave.setType(leaveForm.getType());
        leave.setHalfDay(false);

        if (leaveForm.getType().equals(LeaveRequest.Type.PERMISSION)) {
            leave.setHalfDay(leaveForm.getHalfDay() == 1);
        }

        leaveService.request(leave);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        LeaveRequest leave = leaveService.getRequestById(id);

        if (leave == null) {
            throw new DataNotFoundException("Cuti tidak ditemukan");
        }

        leave.setDeleted(true);
        leaveService.request(leave);

        return "leave/leaverequest/leaverequest.list";
    }
}
