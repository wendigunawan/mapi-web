package com.mapi.ihrd.module.absence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.module.absence.converter.DelayRequestStatusConverter;
import com.mapi.ihrd.module.employee.model.Employee;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = Constant.TABLE_NAME_DELAY_REQUEST)
public class LateRequest extends BaseModel {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date")
    private Date requestDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "duration")
    private int duration;//in day

    @Convert(converter = DelayRequestStatusConverter.class)
    @Column(name = "status")
    private LateRequest.Status status;

    @Column(name = "note")
    private String note;

    @Column(name = "process_note")
    private String processNote;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_by")
    private Employee requestBy;

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getProcessNote() {
        return processNote;
    }

    public void setProcessNote(String processNote) {
        this.processNote = processNote;
    }

    public Employee getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(Employee requestBy) {
        this.requestBy = requestBy;
    }

    public enum Status {

        NEW("new"), APPROVED("approved"), REJECTED("rejected");

        private Status(String val) {

        }


    }
}
