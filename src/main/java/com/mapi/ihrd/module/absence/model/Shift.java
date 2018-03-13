package com.mapi.ihrd.module.absence.model;

import com.mapi.ihrd.model.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "mst_shift")
public class Shift extends BaseModel{

    @NotNull
    @NotEmpty
    @Column(name = "shift_id")
    private String shiftId;

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    @DateTimeFormat(pattern="HH:mm")
    private Date startTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern="HH:mm")
    private Date endTime;

    @Column(name = "description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }
}
