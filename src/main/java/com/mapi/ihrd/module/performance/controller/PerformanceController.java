package com.mapi.ihrd.module.performance.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.module.performance.form.PerformanceForm;
import com.mapi.ihrd.module.performance.model.Component;
import com.mapi.ihrd.module.performance.model.Performance;
import com.mapi.ihrd.module.performance.model.PerformanceView;
import com.mapi.ihrd.module.performance.model.QPerformanceView;
import com.mapi.ihrd.module.performance.queryfilter.PerformanceQueryFilter;
import com.mapi.ihrd.module.performance.service.ComponentService;
import com.mapi.ihrd.module.performance.service.PerformanceService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/web/performance")
public class PerformanceController extends BaseController{

    @Autowired
    PerformanceService performanceService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ComponentService componentService;

    @GetMapping
    public String index() {
        return "performance/performance.list";
    }

    @ModelAttribute("employees")
    public Collection<Employee> getEmployee(){
        return employeeService.find();
    }

    @ModelAttribute("components")
    public Iterable<Component> getComponent(){
        return componentService.findAll();
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datable(JQueryDataTableInput input) {

        PerformanceQueryFilter filter = new PerformanceQueryFilter();
        filter.setPageRequest(createPageRequest(input.getStart(), input.getLength()));
        filter.setSearchText(input.getSearchText());
        Page<PerformanceView> page = performanceService.findView(filter);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model){
        PerformanceForm form = new PerformanceForm();
        form.setPerformanceDate(DateUtil.now());
        model.addAttribute("performance", form);
        return "performance/performance.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Performance performance = performanceService.getById(id);
        PerformanceForm form = new PerformanceForm();
        form.setId(performance.getId());
        form.setPerformanceDate(performance.getPerformanceDate());
        form.setEmployee(performance.getEmployeeId());
        form.setComponent(performance.getComponentId());
        form.setPerformanceValue(performance.getPerformanceValue());
        form.setValueInput(performance.getPerformanceValue());

        model.addAttribute("performance", form);
        return "performance/performance.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid PerformanceForm form, BindingResult result){

        if (result.hasErrors()) {
            return "performance/performance.form";
        }

        Performance performance = new Performance();
        performance.setId(form.getId());
        performance.setEmployeeId(form.getEmployee());
        performance.setComponentId(form.getComponent());
        performance.setPerformanceDate(form.getPerformanceDate());
        performance.setPerformanceValue(form.getValueInput());

        performanceService.save(performance);

        return "redirect:/web/performance";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Performance performance = performanceService.getById(id);

        if (performance == null) {
            throw new DataNotFoundException("Performance tidak ditemukan");
        }

        performance.setDeleted(true);
        performanceService.save(performance);

        return ResponseEntity.ok().build();
    }

}
