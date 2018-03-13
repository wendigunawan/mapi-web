package com.mapi.ihrd.module.absence.model;

import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.module.absence.model.converter.AttendanceReasonConverter;
import com.mapi.ihrd.module.employee.model.Employee;
import org.hibernate.annotations.Type;

import javax.annotation.Nullable;
import javax.jdo.annotations.Unique;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class AttendanceBaseModel extends BaseModel {

    @Column(name = "attendance_date")
    private Date date;

    @Column(name = "attend")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isAttend;

    @Convert(converter = AttendanceReasonConverter.class)
    @Column(name = "reason_type")
    @Nullable
    private Attendance.Reason reason;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean isAttend() {
        return isAttend;
    }

    public void setAttend(Boolean attend) {
        isAttend = attend;
    }

    public Attendance.Reason getReason() {
        return reason;
    }

    public void setReason(Attendance.Reason reason) {
        this.reason = reason;
    }
}
