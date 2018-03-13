package com.mapi.ihrd.module.employee.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.form.EmployeeForm;
import com.mapi.ihrd.module.employee.model.*;
import com.mapi.ihrd.module.employee.service.*;
import com.mapi.ihrd.response.JQueryDataTable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/web/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JobService jobService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private EmployeeEmergencyService emergencyService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CertificateService certificateService;

    @Value("${app.path.slipgaji}")
    private String slipGajiPath;

    @Value("${app.path.pic}")
    private String picPath;

    @ModelAttribute("jobs")
    public Collection<Job> getJobs() {
        return jobService.find();
    }

    @ModelAttribute("departments")
    public Collection<Department> getDepartments() {
        return departmentService.find();
    }

    @ModelAttribute("skills")
    public Iterable<Skill> getSkill(){
        return skillService.find();
    }

    @ModelAttribute("educations")
    public Iterable<Education> getEducations(){
        return educationService.findAll();
    }

    @ModelAttribute("certificates")
    public Iterable<Certificate> getCertificates(){
        return certificateService.find();
    }

    @ModelAttribute("employees")
    public Iterable<Employee> getEmployee(){
        return employeeService.find();
    }

    @ModelAttribute("language")
    public Iterable<Language> getLanguage(){
        return languageService.findAll();
    }

    @GetMapping
    public String index() {
        return "employee/employee.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable datatable(@RequestParam(required = false) Integer start,
                                     @RequestParam(required = false) Integer length,
                                     @RequestParam(required = true) Integer draw) {

        PageRequest pageRequest = createPageRequest(start, length);

        Page<Employee> employeePage = employeeService.find(pageRequest);

        return new JQueryDataTable<>(employeePage.getContent(), employeePage.getTotalElements(), draw);
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession session) {
        model.addAttribute("genderMale", Gender.MALE);
        model.addAttribute("genderFemale", Gender.FEMALE);
        model.addAttribute("statusLeave", Employee.Status.LEAVE);
        model.addAttribute("statusActive", Employee.Status.ACTIVE);
        model.addAttribute("statusResign", Employee.Status.RESIGN);
        return "employee/employee.add";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        Employee employee = employeeService.findById(id);

        EmployeeForm form = new EmployeeForm();

        BeanUtils.copyProperties(employee, form);

        model.addAttribute("employee", form);
        model.addAttribute("genderMale", Gender.MALE);
        model.addAttribute("genderFemale", Gender.FEMALE);
        model.addAttribute("statusLeave", Employee.Status.LEAVE);
        model.addAttribute("statusActive", Employee.Status.ACTIVE);
        model.addAttribute("statusResign", Employee.Status.RESIGN);

        return "employee/employee.form";
    }

    @PostMapping(value = "/save", params = {"addEmergency"})
    public String addEmergency(@ModelAttribute EmployeeForm form){
        form.addEmergency(new EmployeeEmergency());
        return "employee/employee.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute EmployeeForm form) {

        Employee employee = new Employee();

        BeanUtils.copyProperties(form, employee);


//        File f = new File(picPath + "/" + employee.getNik());
//        if (!f.exists() || (f.exists() && !f.isDirectory())) {
//            if (!f.mkdir()) {
//                throw new BusinessException("Gagal membuat directory");
//            }
//        }
//
//        try {
//            FileOutputStream fos = new FileOutputStream(f.getAbsolutePath() + "/" + form.getFile().getOriginalFilename());
//            FileCopyUtils.copy(form.getFile().getInputStream(), fos);
//            employee.setPicture(f.getAbsolutePath() + "/" + form.getFile().getOriginalFilename());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        employeeService.save(employee);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/slipgaji")
    public ResponseEntity uploadSlipGaji(@PathVariable String id, @RequestParam MultipartFile file) throws IOException {


        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        File f = new File(slipGajiPath + "/" + employee.getNik());
        if (!f.exists() || (f.exists() && !f.isDirectory())) {
            if (f.mkdir()) {
                throw new BusinessException("Gagal membuat directory");
            }
        }

        FileOutputStream fos = new FileOutputStream(f.getAbsolutePath() + "/" + file.getName());

        FileCopyUtils.copy(file.getInputStream(), fos);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak ditemukan");
        }

        employee.setDeleted(true);
        employeeService.save(employee);

        return "employee/employee.list";
    }

    @PostMapping("/createAccount")
    public ResponseEntity createAccount(@RequestParam String id) {
        employeeService.createAccount(id);
        return ResponseEntity.ok().build();
    }
}
