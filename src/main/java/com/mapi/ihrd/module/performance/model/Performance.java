package com.mapi.ihrd.module.performance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.module.employee.model.Employee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constant.TABLE_NAME_PERFORMANCE)
public class Performance extends PerformanceBaseModel {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private Employee employeeId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    private Component componentId;


    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Component getComponentId() {
        return componentId;
    }

    public void setComponentId(Component componentId) {
        this.componentId = componentId;
    }
}
