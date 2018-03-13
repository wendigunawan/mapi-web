package com.mapi.ihrd.module.health.api;

import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.health.form.DoctorForm;
import com.mapi.ihrd.module.health.model.Doctor;
import com.mapi.ihrd.module.health.model.Hospital;
import com.mapi.ihrd.module.health.service.DoctorService;
import com.mapi.ihrd.module.health.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorApi {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public ResponseEntity index(@RequestParam(required = true) Integer start,
                                @RequestParam(required = true) Integer length) {

        PageRequest pageRequest = new PageRequest(start, length);
        Page<Doctor> doctors = doctorService.find(pageRequest);

        return ResponseEntity.ok(doctors);

    }

    @PostMapping
    public ResponseEntity add(@RequestBody DoctorForm form) {

        Doctor doctor = new Doctor();
        doctor.setFullName(form.getFullName());
        doctor.setSpecialist(form.getSpecialist());

        Hospital hospital = hospitalService.getById(form.getHospitalId());

        if (hospital == null) {
            throw new DataNotFoundException("Rumah Sakit tidak ditemukan");
        }

        doctorService.save(doctor);

        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable String id, @RequestBody DoctorForm form) {

        Doctor doctor = doctorService.getById(id);

        if (doctor == null) {
            throw new DataNotFoundException("Dokter tidak ditemukan");
        }

        doctorService.save(doctor);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable String id) {

        Doctor doctor = doctorService.getById(id);

        if (doctor == null) {
            throw new DataNotFoundException("Dokter tidak ditemukan");
        }

        return ResponseEntity.ok(doctor);
    }
}
