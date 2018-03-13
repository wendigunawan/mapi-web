package com.mapi.ihrd.module.performance.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.performance.model.Component;
import com.mapi.ihrd.module.performance.queryfilter.ComponentQueryFilter;
import com.mapi.ihrd.module.performance.service.ComponentService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/component")
public class ComponentController extends BaseController{

    @Autowired
    ComponentService componentService;

    @GetMapping
    public String index() {
        return "performance/component/component.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datable(JQueryDataTableInput input) {

        ComponentQueryFilter filter = new ComponentQueryFilter();
        filter.setPageRequest(createPageRequest(input.getStart(), input.getLength()));
        filter.setSearchText(input.getSearchText());
        Page<Component> page = componentService.findAll(filter);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("component", new Component());
        return "performance/component/component.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Component component = componentService.getById(id);
        model.addAttribute("component", component);
        return "performance/component/component.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Component component, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/component/component.form";
        }

        componentService.save(component);

        return "redirect:/web/component";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Component component = componentService.getById(id);

        if (component == null) {
            throw new DataNotFoundException("Komponen tidak ditemukan");
        }

        component.setDeleted(true);
        componentService.save(component);

        return ResponseEntity.ok().build();
    }
}
