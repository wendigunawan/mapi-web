package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.aauth.model.Role;
import com.mapi.ihrd.module.aauth.model.User;
import com.mapi.ihrd.module.aauth.service.RoleService;
import com.mapi.ihrd.module.aauth.service.UserService;
import com.mapi.ihrd.module.employee.dao.EmployeeDao;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.model.QEmployee;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void save(Employee employee) {

        employee.setCreatedDate(DateUtil.now());

        employeeDao.save(employee);
    }

    @Override
    public Employee findById(String id) {
        return employeeDao.findOne(id);
    }

    @Override
    public Employee findByNik(String nik) {

        QEmployee qEmployee = QEmployee.employee;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qEmployee.deleted.eq(false).and(qEmployee.nik.eq(nik)));

        return employeeDao.findOne(booleanBuilder);
    }

    @Override
    public Collection<Employee> find() {
        return employeeDao.findAll();
    }

    @Override
    public Page<Employee> find(int limit, int offset) {

        PageRequest pageRequest = new PageRequest(1, limit);

        Page<Employee> page = employeeDao.findAll(pageRequest);

        return page;
    }

    @Override
    public Page<Employee> find(PageRequest pageRequest) {
        return employeeDao.findAll(pageRequest);
    }

    @Override
    public User createAccount(String id) {

        Employee employee = employeeDao.findOne(id);
        if (employee == null) {
            throw new DataNotFoundException("Pegawai tidak terdaftar");
        }

        User user = userService.findByUsername(employee.getNik());
        if (user != null) {

            if (employee.getId().equals(user.getRefId())) {
                throw new BusinessException("Akun untuk pegawai dengan NIK: " + employee.getNik() + " telah dibuat");
            }
            user.setRefId(employee.getId());

        } else {

            user = userService.findByRefId(employee.getId());
            if (user != null) {
                throw new BusinessException("Akun untuk pegawai dengan NIK: " + employee.getNik() + " telah dibuat");
            }

            Role role = roleService.findByCode(Constant.ROLE_CODE_STAFF);

            user = new User();
            user.setUsername(employee.getNik());
            user.setPasswd("12345");
            user.setFullName(employee.getFullName());
            user.setType(User.Type.EMPLOYEE);
            user.setEmployee(employee);
            user.setRefId(employee.getId());
            user.getRoles().add(role);
        }

        userService.save(user);

        return user;
    }

}
