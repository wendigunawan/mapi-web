package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Skill;
import com.mapi.ihrd.module.employee.service.SkillService;
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
@RequestMapping("/web/skill")
public class SkillController extends BaseController {

    @Autowired
    SkillService skillService;


    @GetMapping
    public String index() {
        return "employee/skill/skill.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        PageRequest pageRequest = new PageRequest(input.getStart(), input.getLength());
        Page<Skill> page = skillService.find(pageRequest);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());

    }

    @GetMapping("/add")
    public String add(Model model) {
        Skill skill = new Skill();
        model.addAttribute("skill", skill);
        return "employee/skill/skill.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Skill skill = skillService.getById(id);
        model.addAttribute("skill", skill);
        return "employee/skill/skill.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Skill skill, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/skill.form";
        }

        skillService.save(skill);

        return "redirect:/web/skill";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Skill skill = skillService.getById(id);

        if (skill == null) {
            throw new DataNotFoundException("Skill tidak ditemukan");
        }

        skill.setDeleted(true);
        skillService.save(skill);

        return "employee/skill/skill.list";
    }
}
