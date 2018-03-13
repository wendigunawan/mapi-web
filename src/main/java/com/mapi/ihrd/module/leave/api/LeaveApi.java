package com.mapi.ihrd.module.leave.api;


import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.module.leave.form.LeaveRequestForm;
import com.mapi.ihrd.module.leave.form.LeaveRequestProcessForm;
import com.mapi.ihrd.module.leave.model.LeaveRequest;
import com.mapi.ihrd.module.leave.model.LeaveRequestView;
import com.mapi.ihrd.module.leave.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leave")
public class LeaveApi {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/request")
    public ResponseEntity findRequest(@RequestParam(required = false) LeaveRequest.Status status,
                                      @RequestParam(required = false) LeaveRequest.Type type) {

        PageRequest pageable = new PageRequest(0, Constant.DISPLAY_LENGTH, Sort.Direction.DESC, "requestDate");

        Page<LeaveRequestView> result;

        if (status != null && type == null) {
            result = leaveService.findRequest(status, pageable);
        } else if (type != null && status == null) {
            result = leaveService.findRequest(type, pageable);
        } else if (type != null && status != null) {
            result = leaveService.findRequest(type, status, pageable);
        } else {
            result = leaveService.findRequest(pageable);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/request")
    public ResponseEntity request(@RequestBody @Valid LeaveRequestForm form) {

        LeaveRequest request = new LeaveRequest();

        if (!StringUtils.hasLength(form.getRequestBy())) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        if (form.getHalfDay() != null) {
            request.setHalfDay(form.getHalfDay() == 1);
        }

        Employee employee = employeeService.findById(form.getRequestBy());
        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        request.setRequestBy(employee);
        request.setType(form.getType());
        request.setStartDate(form.getStartDate());
        request.setEndDate(form.getEndDate());
        request.setNote(form.getNote());

        leaveService.request(request);

        Map<String, Object> result = new HashMap<>();
        result.put("id", request.getId());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/request/{id}/approve")
    public ResponseEntity approve(@PathVariable String id, @RequestBody LeaveRequestProcessForm form) {
        leaveService.approve(id, form.getNote());
        Map<String, String> result = new HashMap<>();
        result.put("id", id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/request/{id}/reject")
    public ResponseEntity reject(@PathVariable String id, @RequestBody LeaveRequestProcessForm form) {
        leaveService.reject(id, form.getNote());
        Map<String, String> result = new HashMap<>();
        result.put("id", id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/request/status")
    public ResponseEntity status(@RequestParam("type") LeaveRequest.Type type) {

        List<Map<String, Object>> result = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();

        if (LeaveRequest.Type.LEAVE.equals(type)) {
            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.NEW.name());
            map.put("count", 3);
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.APPROVED.name());
            map.put("count", 3);
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.REJECTED.name());
            map.put("count", 3);
            result.add(map);

        } else if (LeaveRequest.Type.SICK.equals(type)) {

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
            map.put("count", 2);
            result.add(map);

        } else if (LeaveRequest.Type.PERMISSION.equals(type)) {
            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.NEW.name());
            map.put("count", 2);
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.APPROVED.name());
            map.put("count", 2);
            result.add(map);

            map = new HashMap<>();
            map.put("status", LeaveRequest.Status.REJECTED.name());
            map.put("count", 0);
            result.add(map);
        }

        return ResponseEntity.ok(result);
    }


}
