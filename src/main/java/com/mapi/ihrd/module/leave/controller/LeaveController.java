package com.mapi.ihrd.module.leave.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.leave.form.LeaveForm;
import com.mapi.ihrd.module.leave.model.Leave;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/leave")
public class LeaveController extends BaseController {

    @Autowired
    LeaveService leaveService;

    @GetMapping
    public String index() {
        return "leave/leave.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        PageRequest pageRequest = new PageRequest(input.getStart(), input.getLength());
        Page<Leave> page = leaveService.find(pageRequest);

        return new JQueryDataTable(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        LeaveForm leaveForm = new LeaveForm();
        leaveForm.setStartDate(DateUtil.now());
        leaveForm.setEndDate(DateUtil.now());
        model.addAttribute("leave", leaveForm);
        return "leave/leave.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Leave leave = leaveService.getById(id);

        if (leave == null) {
            throw new DataNotFoundException("Cuti tidak ditemukan");
        }

        LeaveForm leaveForm = new LeaveForm();
        leaveForm.setId(leave.getId());
        leaveForm.setStartDate(leave.getStartDate());
        leaveForm.setEndDate(leave.getEndDate());
        leaveForm.setDuration(leave.getDuration());
        leaveForm.setNote(leave.getNote());
        model.addAttribute("leave", leaveForm);
        return "leave/leave.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute @Valid LeaveForm leaveForm, BindingResult result) {

        if (result.hasErrors()) {
            throw new BusinessException("Error");
        }

        Leave leave = new Leave();

        if (StringUtils.hasLength(leaveForm.getId())) {
            leave.setId(leaveForm.getId());
        }

        leave.setStartDate(leaveForm.getStartDate());
        leave.setEndDate(leaveForm.getEndDate());
        leave.setDuration(leaveForm.getDuration());
        leave.setNote(leaveForm.getNote());

        leaveService.save(leave);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Leave leave = leaveService.getById(id);

        if (leave == null) {
            throw new DataNotFoundException("Cuti tidak ditemukan");
        }

        leave.setDeleted(true);
        leaveService.save(leave);

        return "leave/leave.list";
    }
}
