package com.mapi.ihrd.module.leave.model;

import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.module.leave.model.converter.LeaveRequestStatusConverter;
import com.mapi.ihrd.module.leave.model.converter.LeaveRequestTypeConverter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class LeaveRequestBaseModel extends BaseModel {

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

    @Column(name = "half_day")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isHalfDay;

    @Convert(converter = LeaveRequestTypeConverter.class)
    @Column(name = "type")
    private LeaveRequest.Type type;

    @Convert(converter = LeaveRequestStatusConverter.class)
    @Column(name = "status")
    private LeaveRequest.Status status;

    @Column(name = "note")
    private String note;

    @Column(name = "process_note")
    private String processNote;

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

    public Boolean getHalfDay() {
        return isHalfDay;
    }

    public void setHalfDay(Boolean halfDay) {
        isHalfDay = halfDay;
    }

    public LeaveRequest.Type getType() {
        return type;
    }

    public void setType(LeaveRequest.Type type) {
        this.type = type;
    }

    public LeaveRequest.Status getStatus() {
        return status;
    }

    public void setStatus(LeaveRequest.Status status) {
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
}
