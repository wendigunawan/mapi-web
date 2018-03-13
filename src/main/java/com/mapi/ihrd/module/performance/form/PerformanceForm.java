package com.mapi.ihrd.module.performance.form;

import com.mapi.ihrd.module.employee.model.Employee;
import com.mapi.ihrd.module.performance.model.Component;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PerformanceForm implements Serializable{

    private String id;
    private String employeeId;
    private String componentId;
    private Employee employee;
    private Component component;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date performanceDate;
    private int performanceValue;
    private int valueInput;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(Date performanceDate) {
        this.performanceDate = performanceDate;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getPerformanceValue() {
        return performanceValue;
    }

    public void setPerformanceValue(int performanceValue) {
        this.performanceValue = performanceValue;
    }

    public int getValueInput() {
        return valueInput;
    }

    public void setValueInput(int valueInput) {
        this.valueInput = valueInput;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
}
