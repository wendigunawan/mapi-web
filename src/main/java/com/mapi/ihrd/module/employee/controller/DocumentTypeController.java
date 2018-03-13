package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.DocumentType;
import com.mapi.ihrd.module.employee.service.DocumentTypeService;
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
@RequestMapping("/web/document_type")
public class DocumentTypeController extends BaseController {

    @Autowired
    DocumentTypeService documentTypeService;

    @GetMapping
    public String index() {
        return "employee/document/document_type.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(JQueryDataTableInput input) {

        PageRequest pageRequest = new PageRequest(input.getStart(), input.getLength());
        Page<DocumentType> page = documentTypeService.find(pageRequest);

        return new JQueryDataTable<>(page.getContent(), page.getTotalElements(), input.getDraw());

    }

    @GetMapping("/add")
    public String add(Model model) {
        DocumentType documentType = new DocumentType();
        model.addAttribute("document", documentType);
        return "employee/document/document_type.form";
    }

    @GetMapping("/edit/{id}")
    public String add(@PathVariable String id, Model model) {
        DocumentType documentType = documentTypeService.getById(id);
        model.addAttribute("document", documentType);
        return "employee/document/document_type.form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid DocumentType documentType, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/document/document_type.form";
        }

        documentTypeService.save(documentType);

        return "redirect:/web/document_type";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        DocumentType documentType = documentTypeService.getById(id);

        if (documentType == null) {
            throw new DataNotFoundException("Jenis Dokumen tidak ditemukan");
        }

        documentType.setDeleted(true);
        documentTypeService.save(documentType);

        return "employee/document/document_type.list";
    }
}
