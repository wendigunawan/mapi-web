package com.mapi.ihrd.module.absence.model;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.module.employee.model.Employee;

import javax.persistence.*;

@Entity
@Table(name = Constant.TABLE_NAME_ATTENDANCE, uniqueConstraints = {@UniqueConstraint(columnNames={"employee_id", "shift", "attendance_date"})})
public class Attendance extends AttendanceBaseModel {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "shift", referencedColumnName = "shift_id")
    private Shift shift;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public enum Reason {

        LEAVE("leave"), ALPHA("alpha"), SICK("sick");

        private Reason(String val) {

        }

    }
}
