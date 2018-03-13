package com.mapi.ihrd.module.absence.api;


import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.absence.form.DelayRequestForm;
import com.mapi.ihrd.module.absence.model.LateRequest;
import com.mapi.ihrd.module.absence.service.LateService;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/delay")
@RestController
public class DelayApi {

    @Autowired
    private LateService delayService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/request")
    public ResponseEntity request(@RequestBody DelayRequestForm form) {

        Employee employee = employeeService.findById(form.getRequestBy());

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        LateRequest request = new LateRequest();
        request.setRequestBy(employee);
        request.setStartDate(form.getStartDate());
        request.setEndDate(form.getEndDate());
        request.setNote(form.getNote());
        request.setDuration(0);//TODO implementasi perhitungan

        delayService.request(request);

        return ResponseEntity.ok(request);
    }

    @GetMapping("/request")
    public ResponseEntity findRequest() {
        PageRequest pageRequest = new PageRequest(0, Constant.DISPLAY_LENGTH, Sort.Direction.DESC, "requestDate");
        return ResponseEntity.ok(delayService.find(pageRequest));
    }

}
