package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Job;
import com.mapi.ihrd.module.employee.model.Religion;
import com.mapi.ihrd.module.employee.service.JobService;
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
@RequestMapping("/web/job")
public class JobController extends BaseController{

    @Autowired
    private JobService jobService;

    @GetMapping
    public String index() {
        return "employee/job/job.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datable(JQueryDataTableInput input) {

        PageRequest pageRequest = createPageRequest(input.getStart(), input.getLength());
        Page<Job> page = jobService.find(pageRequest);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("job", new Job());
        return "employee/job/job.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Job job = jobService.getById(id);
        model.addAttribute("job", job);
        return "employee/job/job.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Job job, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/job/job.form";
        }

        jobService.save(job);

        return "redirect:/web/job";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Job job = jobService.getById(id);

        if (job == null) {
            throw new DataNotFoundException("Jabatan tidak ditemukan");
        }

        job.setDeleted(true);
        jobService.save(job);

        return ResponseEntity.ok().build();
    }

}
