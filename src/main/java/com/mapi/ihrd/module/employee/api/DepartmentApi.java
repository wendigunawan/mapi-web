package com.mapi.ihrd.module.employee.api;


import com.mapi.ihrd.module.employee.model.Department;
import com.mapi.ihrd.module.employee.service.DepartmentService;
import com.mapi.ihrd.response.DataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/department")
public class DepartmentApi {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity index() {

        Collection<Department> departments = departmentService.find();

        DataList<Department> dataList = new DataList<Department>(departments);

        return ResponseEntity.ok(dataList);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Department department) {

        departmentService.save(department);

        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable String id, @RequestBody Department department) {

        departmentService.save(department);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable String id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }


}
