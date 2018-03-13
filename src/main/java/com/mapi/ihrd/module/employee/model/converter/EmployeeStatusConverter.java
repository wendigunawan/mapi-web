package com.mapi.ihrd.module.employee.model.converter;

import com.mapi.ihrd.module.employee.model.Employee;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmployeeStatusConverter implements AttributeConverter<Employee.Status, String> {

    @Override
    public String convertToDatabaseColumn(Employee.Status status) {
        return status.name();
    }

    @Override
    public Employee.Status convertToEntityAttribute(String s) {

        if (Employee.Status.LEAVE.name().equalsIgnoreCase(s)) {
            return Employee.Status.LEAVE;
        } else if (Employee.Status.ACTIVE.name().equalsIgnoreCase(s)) {
            return Employee.Status.ACTIVE;
        } else if (Employee.Status.RESIGN.name().equalsIgnoreCase(s)) {
            return Employee.Status.RESIGN;
        }

        return null;
    }
}
