package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Bank;
import com.mapi.ihrd.module.employee.service.BankService;
import com.mapi.ihrd.response.JQueryDataTable;
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
@RequestMapping("/web/bank")
public class BankController extends BaseController {

    @Autowired
    BankService bankService;

    @GetMapping
    public String index() {
        return "employee/bank/bank.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(@RequestParam(required = false) Integer start,
                                     @RequestParam(required = false) Integer length,
                                     @RequestParam Integer draw) {

        PageRequest pageRequest = new PageRequest(start, length);
        Page<Bank> page = bankService.find(pageRequest);

        return new JQueryDataTable(page.getContent(), page.getTotalElements(), draw);

    }

    @GetMapping("/add")
    public String add(Model model) {
        Bank bank = new Bank();
        model.addAttribute("bank", bank);
        return "employee/bank/bank.form";
    }

    @GetMapping("/edit/{id}")
    public String add(@PathVariable String id, Model model) {
        Bank bank = bankService.getById(id);
        model.addAttribute("bank", bank);
        return "employee/bank/bank.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Bank bank, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/bank/bank.form";
        }

        bankService.save(bank);

        return "redirect:/web/bank";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Bank bank = bankService.getById(id);

        if (bank == null) {
            throw new DataNotFoundException("Bank tidak ditemukan");
        }

        bank.setDeleted(true);
        bankService.save(bank);

        return ResponseEntity.ok().build();
    }
}
