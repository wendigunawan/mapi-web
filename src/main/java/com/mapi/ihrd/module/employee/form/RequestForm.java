package com.mapi.ihrd.module.employee.form;

import com.mapi.ihrd.module.employee.model.RequestType;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class RequestForm {

    @NotNull
    private RequestType type;

    @NotNull
    private String requestBy;

    private String note;
    private Date startDate;
    private Date endDate;
    private Integer halfDay;

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public Integer getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(Integer halfDay) {
        this.halfDay = halfDay;
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
}
