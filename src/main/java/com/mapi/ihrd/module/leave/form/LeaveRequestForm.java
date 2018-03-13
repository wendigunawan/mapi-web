package com.mapi.ihrd.module.leave.form;

import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.leave.model.LeaveRequest;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class LeaveRequestForm {

    @NotNull
    private LeaveRequest.Type type;

    @NotNull
    @NotEmpty
    private Employee employeeId;


    @NotNull
    @NotEmpty
    private String requestBy;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private int duration;
    private String note;

    private Integer halfDay;


    public LeaveRequest.Type getType() {
        return type;
    }

    public void setType(LeaveRequest.Type type) {
        this.type = type;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(Integer halfDay) {
        this.halfDay = halfDay;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
