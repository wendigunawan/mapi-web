package com.mapi.ihrd.module.health.service;

import com.mapi.ihrd.module.health.form.DoctorForm;
import com.mapi.ihrd.module.health.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DoctorService {

    Doctor getById(String id);


    String add(DoctorForm form);

    void edit(DoctorForm form);

    void save(Doctor doctor);

    Page<Doctor> find(PageRequest pageRequest);
}
