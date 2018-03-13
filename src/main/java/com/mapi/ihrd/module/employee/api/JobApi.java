package com.mapi.ihrd.module.employee.api;

import com.mapi.ihrd.response.DataList;
import com.mapi.ihrd.module.employee.form.JobForm;
import com.mapi.ihrd.module.employee.model.Job;
import com.mapi.ihrd.module.employee.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/job")
public class JobApi {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity index() {

        Collection<Job> jobs = jobService.find();

        DataList<Job> dataList = new DataList<Job>(jobs);

        return ResponseEntity.ok(dataList);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody JobForm form) {

        Job job = new Job();

        copy(form, job);

        jobService.save(job);

        return ResponseEntity.ok(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable String id, @RequestBody JobForm form) {

        Job job = jobService.getById(id);

        copy(form, job);

        jobService.save(job);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable String id) {
        return ResponseEntity.ok(jobService.getById(id));
    }

    private void copy(JobForm source, Job dest) {
        if (StringUtils.hasLength(source.getName())) {
            dest.setName(source.getName());
        }

        if (StringUtils.hasLength(source.getDescription())) {
            dest.setDescription(source.getDescription());
        }
    }

}
