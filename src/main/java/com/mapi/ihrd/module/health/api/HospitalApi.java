package com.mapi.ihrd.module.health.api;


import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.health.form.HospitalForm;
import com.mapi.ihrd.module.health.model.Hospital;
import com.mapi.ihrd.module.health.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hospital")
public class HospitalApi {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public ResponseEntity index(@RequestParam(required = true) Integer start,
                                @RequestParam(required = true) Integer length) {

        PageRequest pageRequest = new PageRequest(start, length);
        Page<Hospital> hospitals = hospitalService.find(pageRequest);

        return ResponseEntity.ok(hospitals);

    }

    @PostMapping
    public ResponseEntity add(@RequestBody HospitalForm form) {

        String id = hospitalService.add(form);
        form.setId(id);

        return ResponseEntity.ok(form);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable String id, @RequestBody HospitalForm form) {

        hospitalService.edit(form);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable String id) {

        Hospital hospital = hospitalService.getById(id);

        if (hospital == null) {
            throw new DataNotFoundException("Rumah Sakit tidak ditemukan");
        }

        return ResponseEntity.ok(hospital);
    }


}
