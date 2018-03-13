package com.mapi.ihrd.module.absence.service.impl;

import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.exception.InvalidParameterValueException;
import com.mapi.ihrd.module.absence.controller.AbsenceController;
import com.mapi.ihrd.module.absence.dao.AttendanceDao;
import com.mapi.ihrd.module.absence.dao.AttendanceViewDao;
import com.mapi.ihrd.module.absence.form.AttendanceAddForm;
import com.mapi.ihrd.module.absence.model.Attendance;
import com.mapi.ihrd.module.absence.model.AttendanceView;
import com.mapi.ihrd.module.absence.model.QAttendance;
import com.mapi.ihrd.module.absence.model.QAttendanceView;
import com.mapi.ihrd.module.absence.queryfilter.AttendanceQueryFilter;
import com.mapi.ihrd.module.absence.service.AttendaceService;
import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.employee.service.EmployeeService;
import com.mapi.ihrd.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class AttendaceServiceImpl implements AttendaceService {

    private static final Logger LOG = LoggerFactory.getLogger(AttendaceServiceImpl.class);

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private AttendanceViewDao attendanceViewDao;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void save(Attendance attendance) {

        attendance.setCreatedDate(DateUtil.now());

        attendanceDao.save(attendance);
    }

    @Override
    public Iterable<Attendance> findAll(AttendanceQueryFilter filter) {

        QAttendance attendance = QAttendance.attendance;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(attendance.deleted.eq(false));

        return attendanceDao.findAll(builder, filter.getPageRequest());
    }

    @Override
    public Page<Attendance> find(AttendanceQueryFilter filter) {

        QAttendance attendance = QAttendance.attendance;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(attendance.deleted.eq(false));

        return attendanceDao.findAll(builder, filter.getPageRequest());
    }

    @Override
    public Iterable<AttendanceView> findAllView(AttendanceQueryFilter filter) {

        QAttendanceView attendanceView = QAttendanceView.attendanceView;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(attendanceView.deleted.eq(false));

        return attendanceViewDao.findAll(builder, filter.getPageRequest());
    }

    @Override
    public Page<AttendanceView> findView(AttendanceQueryFilter filter) {

        QAttendanceView attendanceView = QAttendanceView.attendanceView;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(attendanceView.deleted.eq(false));

        if (!filter.getSearchText().isEmpty()) {
            builder.or(attendanceView.shiftName.likeIgnoreCase(filter.getSearchText()));
            builder.or(attendanceView.fullName.likeIgnoreCase(filter.getSearchText()));
            builder.or(attendanceView.jobName.likeIgnoreCase(filter.getSearchText()));
            builder.or(attendanceView.departmentName.likeIgnoreCase(filter.getSearchText()));
        }

        return attendanceViewDao.findAll(builder, filter.getPageRequest());
    }

    @Override
    public void add(AttendanceAddForm form) {

        Employee employee = employeeService.findById(form.getEmployeeId());

        if (employee == null) {
            throw new DataNotFoundException("Pegawai degan id: " + form.getEmployeeId() + " tidak terdaftar");
        }

        if (form.getAttendances() == null || !form.getAttendances().isEmpty()) {
            throw new InvalidParameterValueException("Tidak ada data kehadiran yang akan ditambahkan");
        }

        Date today = DateUtil.now();


        for (Attendance attendance : form.getAttendances()) {
            attendance.setEmployee(employee);
            attendance.setCreatedDate(today);
            attendance.setUpdatedDate(today);
        }


    }
}
