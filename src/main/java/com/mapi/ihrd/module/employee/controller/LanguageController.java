package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Education;
import com.mapi.ihrd.module.employee.model.Language;
import com.mapi.ihrd.module.employee.service.LanguageService;
import com.mapi.ihrd.response.JQueryDataTable;
import com.mapi.ihrd.response.JQueryDataTableInput;
import org.apache.commons.codec.language.bm.Lang;
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
@RequestMapping("/web/language")
public class LanguageController extends BaseController {

    @Autowired
    LanguageService languageService;

    @GetMapping
    public String index() {
        return "employee/language/language.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable<Language> dataTable(JQueryDataTableInput input) {

        PageRequest pageRequest = createPageRequest(input.getStart(), input.getLength());
        Page<Language> page = languageService.find(pageRequest);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("language", new Education());
        return "employee/language/language.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Language language = languageService.getById(id);
        model.addAttribute("language", language);
        return "employee/language/language.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Language language, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/language/language.form";
        }

        languageService.save(language);

        return "redirect:/web/language";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Language language = languageService.getById(id);

        if (language == null) {
            throw new DataNotFoundException("Bahasa tidak ditemukan");
        }

        language.setDeleted(true);
        languageService.save(language);

        return ResponseEntity.ok().build();
    }
}
