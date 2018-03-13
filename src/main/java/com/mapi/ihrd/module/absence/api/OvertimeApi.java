package com.mapi.ihrd.module.absence.api;


import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.absence.form.OvertimeRequestForm;
import com.mapi.ihrd.module.absence.model.OvertimeRequest;
import com.mapi.ihrd.module.absence.service.OvertimeService;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/overtime")
public class OvertimeApi {

    @Autowired
    private OvertimeService overtimeService;

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/request")
    public ResponseEntity request(@RequestBody OvertimeRequestForm form) {

        Employee employee = employeeService.findById(form.getRequestBy());

        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }


        OvertimeRequest request = new OvertimeRequest();
        request.setRequestBy(employee);
        request.setNote(form.getNote());
        request.setDuration(0);//TODO implementasi perhitungan

        overtimeService.request(request);

        return ResponseEntity.ok(request);
    }


    /*@GetMapping("/request/status")
    public ResponseEntity status() {

        List<Map<String, Object>> result = new ArrayList<>();

        Map<String, Object> map;

        map = new HashMap<>();
        map.put("status", LeaveRequest.Status.NEW.name());
        map.put("count", 3);
        result.add(map);

        map = new HashMap<>();
        map.put("status", LeaveRequest.Status.APPROVED.name());
        map.put("count", 1);
        result.add(map);

        map = new HashMap<>();
        map.put("status", LeaveRequest.Status.REJECTED.name());
        map.put("count", 3);
        result.add(map);

        return ResponseEntity.ok(result);
    }*/

}
