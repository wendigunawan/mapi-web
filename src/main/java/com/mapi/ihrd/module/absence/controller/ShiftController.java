package com.mapi.ihrd.module.absence.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.absence.dao.ShiftDao;
import com.mapi.ihrd.module.absence.model.Shift;
import com.mapi.ihrd.module.absence.queryfilter.ShiftQueryFilter;
import com.mapi.ihrd.module.absence.service.ShiftService;
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
@RequestMapping("/web/shift")
public class ShiftController extends BaseController {

    @Autowired
    ShiftService shiftService;

    @GetMapping
    public String index() {
        return "absence/shift/shift.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        ShiftQueryFilter filter = new ShiftQueryFilter();
        PageRequest pr = createPageRequest(input.getStart(), input.getLength());
        filter.setPageRequest(pr);
        filter.setSearchText(input.getSearchText());

        Page<Shift> page = shiftService.find(filter);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        Shift shift = new Shift();
        model.addAttribute("shift", shift);
        return "absence/shift/shift.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Shift shift, BindingResult result) {

        if (result.hasErrors()) {
            return "absence/shift/shift.form";
        }

        shiftService.save(shift);

        return "redirect:/web/shift";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Shift shift = shiftService.getById(id);
        model.addAttribute("shift", shift);
        return "absence/shift/shift.form";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Shift shift = shiftService.getById(id);

        if (shift == null) {
            throw new DataNotFoundException("Shift tidak ditemukan");
        }

        shift.setDeleted(true);
        shiftService.save(shift);

        return ResponseEntity.ok().build();
    }
}
