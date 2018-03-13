package com.mapi.ihrd.module.leave.model.converter;

import com.mapi.ihrd.module.leave.model.LeaveRequest;

import javax.persistence.AttributeConverter;

public class LeaveRequestTypeConverter implements AttributeConverter<LeaveRequest.Type, String> {
    @Override
    public String convertToDatabaseColumn(LeaveRequest.Type type) {
        return type.name();
    }

    @Override
    public LeaveRequest.Type convertToEntityAttribute(String s) {

        if (LeaveRequest.Type.LEAVE.name().equalsIgnoreCase(s)) {
            return LeaveRequest.Type.LEAVE;
        } else if (LeaveRequest.Type.SICK.name().equalsIgnoreCase(s)) {
            return LeaveRequest.Type.SICK;
        } else if (LeaveRequest.Type.PERMISSION.name().equalsIgnoreCase(s)) {
            return LeaveRequest.Type.PERMISSION;
        }

        return null;
    }
}
