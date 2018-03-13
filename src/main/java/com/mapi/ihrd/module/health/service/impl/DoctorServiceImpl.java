package com.mapi.ihrd.module.health.service.impl;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.health.dao.DoctorDao;
import com.mapi.ihrd.module.health.dao.HospitalDao;
import com.mapi.ihrd.module.health.form.DoctorForm;
import com.mapi.ihrd.module.health.model.Doctor;
import com.mapi.ihrd.module.health.model.Hospital;
import com.mapi.ihrd.module.health.service.DoctorService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.Valid;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private HospitalDao hospitalDao;

    @Override
    public Doctor getById(String id) {
        return doctorDao.findOne(id);
    }

    @Override
    public String add(@Valid DoctorForm form) {

        Doctor doctor = new Doctor();
        doctor.setFullName(form.getFullName());
        doctor.setSpecialist(form.getSpecialist());

        Hospital hospital = hospitalDao.findOne(form.getHospitalId());

        if (hospital == null) {
            throw new DataNotFoundException("Rumah Sakit tidak terdaftar");
        }

        doctor.setHospital(hospital);
        doctor.setCreatedDate(DateUtil.now());
        doctor.setUpdatedDate(DateUtil.now());

        doctorDao.save(doctor);

        return doctor.getId();
    }

    @Override
    public void edit(DoctorForm form) {

        if (!StringUtils.hasLength(form.getId())) {
            throw new DataNotFoundException("Data yang akan diubah tidak ditemukan");
        }

        Doctor doctor = doctorDao.findOne(form.getId());

        if (doctor == null) {
            throw new DataNotFoundException("Data yang akan diubah tidak ditemukan");
        }

        boolean anyChanges = false;

        if (StringUtils.hasLength(form.getFullName())) {
            anyChanges = true;
        }

        if (StringUtils.hasLength(form.getSpecialist())) {
            anyChanges = true;
        }

        if (StringUtils.hasLength(form.getHospitalId())) {
            anyChanges = true;
        }

        if (!anyChanges) {
            throw new BusinessException("Tidak ada perubahan terhadap data ini");
        }

        doctor.setUpdatedDate(DateUtil.now());

        doctorDao.save(doctor);
    }

    @Override
    public void save(Doctor doctor) {
        doctorDao.save(doctor);
    }

    @Override
    public Page<Doctor> find(PageRequest pageRequest) {
        return doctorDao.findAll(pageRequest);
    }
}
