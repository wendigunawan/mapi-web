package com.mapi.ihrd.module.absence.form;

import com.mapi.ihrd.module.absence.model.Attendance;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AttendanceAddForm {

    @NotNull
    @NotEmpty
    private String employeeId;

    @NotNull
    private List<Attendance> attendances = new ArrayList<>();

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
