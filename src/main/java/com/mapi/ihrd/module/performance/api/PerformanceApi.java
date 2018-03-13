package com.mapi.ihrd.module.performance.api;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.module.performance.form.PerformanceForm;
import com.mapi.ihrd.module.performance.model.Performance;
import com.mapi.ihrd.module.performance.queryfilter.PerformanceQueryFilter;
import com.mapi.ihrd.module.performance.service.ComponentService;
import com.mapi.ihrd.module.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/performance")
public class PerformanceApi extends BaseController {

    @Autowired
    PerformanceService performanceService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ComponentService componentService;

    @GetMapping
    public ResponseEntity index(@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {

        if (limit == null) {
            limit = Constant.DISPLAY_LENGTH;
        }

        if (offset == null) {
            offset = Integer.valueOf(0);
        }

        PerformanceQueryFilter filter = new PerformanceQueryFilter();
        filter.setPageRequest(createPageRequest(offset, limit));
        filter.setSearchText("");

        return ResponseEntity.ok(performanceService.findView(filter));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody PerformanceForm form) {
        Performance performance = new Performance();
        performance.setEmployeeId(employeeService.findById(form.getEmployeeId()));
        performance.setComponentId(componentService.getById(form.getComponentId()));
        performance.setPerformanceDate(form.getPerformanceDate());
        performance.setPerformanceValue(form.getValueInput());

        performanceService.save(performance);
        return ResponseEntity.ok(form);
    }

}
