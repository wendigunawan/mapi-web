package com.mapi.ihrd.module.employee.model;

import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.config.Constant;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constant.TABLE_NAME_JOB_HISTORY)
public class JobHistory extends BaseModel {

    @ManyToOne()
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Department department;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
}
