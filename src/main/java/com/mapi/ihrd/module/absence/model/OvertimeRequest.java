package com.mapi.ihrd.module.absence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.module.absence.model.converter.OvertimeRequestStatusConverter;
import com.mapi.ihrd.module.employee.model.Employee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constant.TABLE_NAME_OVERTIME_REQUEST)
public class OvertimeRequest extends OvertimeRequestBaseModel {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_by")
    private Employee requestBy;

    public Employee getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(Employee requestBy) {
        this.requestBy = requestBy;
    }
}
