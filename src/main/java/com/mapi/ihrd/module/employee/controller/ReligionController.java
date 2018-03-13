package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Religion;
import com.mapi.ihrd.module.employee.service.ReligionService;
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
@RequestMapping("/web/religion")
public class ReligionController extends BaseController {

    @Autowired
    private ReligionService religionService;

    @GetMapping
    public String index() {
        return "employee/religion/religion.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable<Religion> dataTable(JQueryDataTableInput input) {

        PageRequest pageRequest = new PageRequest(input.getStart(), input.getLength());
        Page<Religion> page = religionService.find(pageRequest);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("religion", new Religion());
        return "employee/religion/religion.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Religion religion = religionService.getById(id);
        model.addAttribute("religion", religion);
        return "employee/religion/religion.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Religion religion, BindingResult result) {

        if (result.hasErrors()) {
            return "education.form";
        }

        religionService.save(religion);

        return "redirect:/web/religion";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Religion religion = religionService.getById(id);

        if (religion == null) {
            throw new DataNotFoundException("Religion tidak ditemukan");
        }

        religion.setDeleted(true);
        religionService.save(religion);

        return ResponseEntity.ok().build();
    }

}
