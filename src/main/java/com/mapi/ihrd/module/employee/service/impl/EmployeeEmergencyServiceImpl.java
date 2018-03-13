package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.EmergencyDao;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.model.EmployeeEmergency;
import com.mapi.ihrd.module.employee.service.EmployeeEmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class EmployeeEmergencyServiceImpl implements EmployeeEmergencyService {

    @Autowired
    EmergencyDao emergencyDao;

    @Override
    public EmployeeEmergency getById(String id) {
        return emergencyDao.findOne(id);
    }

    @Override
    public Page<EmployeeEmergency> find(PageRequest pageRequest) {
        return emergencyDao.findAll(pageRequest);
    }

    @Override
    public Collection<EmployeeEmergency> find() {
        return emergencyDao.findAll();
    }

    @Override
    public Collection<EmployeeEmergency> getByEmployee(Employee employee) {
        return null;
    }

    @Override
    public void save(EmployeeEmergency emergency) {
        emergencyDao.save(emergency);
    }
}
