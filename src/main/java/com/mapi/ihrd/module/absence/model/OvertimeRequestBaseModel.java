package com.mapi.ihrd.module.absence.model;

import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.module.absence.model.converter.OvertimeRequestStatusConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class OvertimeRequestBaseModel extends BaseModel{

    @Column(name = "request_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date requestDate;

    @Column(name = "start_time")
    @DateTimeFormat(pattern="HH:mm")
    private Date startTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern="HH:mm")
    private Date endTime;

    @Column(name = "duration")
    private int duration = 0;

    @Column(name = "note")
    private String note;

    @Convert(converter = OvertimeRequestStatusConverter.class)
    @Column(name = "status")
    private Status status;

    @Column(name = "process_note")
    private String processNote;

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getProcessNote() {
        return processNote;
    }

    public void setProcessNote(String processNote) {
        this.processNote = processNote;
    }

    public enum Status {

        NEW("new"), APPROVED("approved"), REJECTED("rejected");

        private Status(String val) {

        }


    }
}
