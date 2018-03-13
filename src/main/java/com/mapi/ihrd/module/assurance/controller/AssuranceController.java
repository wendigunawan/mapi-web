package com.mapi.ihrd.module.assurance.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.assurance.model.Assurance;
import com.mapi.ihrd.module.assurance.queryfilter.AssuranceQueryFilter;
import com.mapi.ihrd.module.assurance.service.AssuranceService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/assurance")
public class AssuranceController extends BaseController{

    @Autowired
    AssuranceService assuranceService;

    @GetMapping
    public String index() {
        return "assurance/assurance.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        AssuranceQueryFilter queryFilter = new AssuranceQueryFilter();

        PageRequest pageRequest = new PageRequest(input.getStart(), input.getLength());
        queryFilter.setPageRequest(pageRequest);
        queryFilter.setSearchText(input.getSearchText());

        Page<Assurance> page = assuranceService.find(queryFilter);

        return new JQueryDataTable(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Assurance assurance = assuranceService.getById(id);
        model.addAttribute("assurance", assurance);
        return "assurance/assurance.form";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Assurance assurance = new Assurance();
        model.addAttribute("assurance", assurance);
        return "assurance/assurance.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Assurance assurance, BindingResult result) {

        if (result.hasErrors()) {
            return "assurance/assurance.form";
        }

        assuranceService.save(assurance);

        return "redirect:/web/assurance";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        Assurance assurance = assuranceService.getById(id);

        if (assurance == null) {
            throw new DataNotFoundException("Asuransi tidak ditemukan");
        }

        assurance.setDeleted(true);
        assuranceService.save(assurance);

        return "assurance/assurance.list";
    }
}
