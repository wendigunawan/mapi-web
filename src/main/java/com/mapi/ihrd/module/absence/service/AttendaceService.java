package com.mapi.ihrd.module.absence.service;

import com.mapi.ihrd.module.absence.form.AttendanceAddForm;
import com.mapi.ihrd.module.absence.model.Attendance;
import com.mapi.ihrd.module.absence.model.AttendanceView;
import com.mapi.ihrd.module.absence.queryfilter.AttendanceQueryFilter;
import org.springframework.data.domain.Page;

public interface AttendaceService {

    void save(Attendance attendance);

    Iterable<Attendance> findAll(AttendanceQueryFilter filter);

    Page<Attendance> find(AttendanceQueryFilter filter);

    Iterable<AttendanceView> findAllView(AttendanceQueryFilter filter);

    Page<AttendanceView> findView(AttendanceQueryFilter filter);

    void add(AttendanceAddForm form);
}
