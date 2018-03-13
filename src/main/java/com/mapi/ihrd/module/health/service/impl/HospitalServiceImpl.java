package com.mapi.ihrd.module.health.service.impl;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.health.dao.HospitalDao;
import com.mapi.ihrd.module.health.form.HospitalForm;
import com.mapi.ihrd.module.health.model.Hospital;
import com.mapi.ihrd.module.health.service.HospitalService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.Collection;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalDao hospitalDao;

    @Override
    public Hospital getById(String id) {
        return hospitalDao.findOne(id);
    }

    @Override
    public String add(@Valid HospitalForm form) {

        Hospital hospital = new Hospital();

        hospital.setName(form.getName());
        hospital.setAddress(form.getAddress());
        hospital.setPhoneNumber1(form.getPhoneNumber1());
        hospital.setPhoneNumber2(form.getPhoneNumber2());
        hospital.setCreatedDate(DateUtil.now());
        hospital.setUpdatedDate(DateUtil.now());

        hospitalDao.save(hospital);

        return hospital.getId();
    }

    @Override
    public void edit(HospitalForm form) {

        if (!StringUtils.hasLength(form.getId())) {
            throw new DataNotFoundException("Data yang akan diubah tidak ditemukan");
        }

        Hospital hospital = hospitalDao.findOne(form.getId());

        if (hospital == null) {
            throw new DataNotFoundException("Data yang akan diubah tidak ditemukan");
        }

        boolean anyChanges = false;

        if (StringUtils.hasLength(form.getName())) {
            anyChanges = true;
            hospital.setName(form.getName());
        }

        if (StringUtils.hasLength(form.getAddress())) {
            anyChanges = true;
            hospital.setAddress(form.getAddress());
        }

        if (StringUtils.hasLength(form.getPhoneNumber1())) {
            anyChanges = true;
            hospital.setPhoneNumber1(form.getPhoneNumber1());
        }

        if (StringUtils.hasLength(form.getPhoneNumber2())) {
            anyChanges = true;
            hospital.setPhoneNumber2(form.getPhoneNumber2());
        }

        if (!anyChanges) {
            throw new BusinessException("Tidak ada perubahan terhadap data ini");
        }

        hospital.setUpdatedDate(DateUtil.now());

        hospitalDao.save(hospital);

    }

    @Override
    public Page<Hospital> find(PageRequest pageRequest) {
        return hospitalDao.findAll(pageRequest);
    }

    @Override
    public Collection<Hospital> findAll() {
        return hospitalDao.findAll();
    }
}
