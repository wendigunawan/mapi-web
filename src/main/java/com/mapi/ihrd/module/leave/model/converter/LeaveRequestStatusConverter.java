package com.mapi.ihrd.module.leave.model.converter;

import com.mapi.ihrd.module.leave.model.LeaveRequest;

import javax.persistence.AttributeConverter;

public class LeaveRequestStatusConverter implements AttributeConverter<LeaveRequest.Status, String> {

    @Override
    public String convertToDatabaseColumn(LeaveRequest.Status status) {
        return status.name();
    }

    @Override
    public LeaveRequest.Status convertToEntityAttribute(String s) {

        if (LeaveRequest.Status.NEW.name().equalsIgnoreCase(s)) {
            return LeaveRequest.Status.NEW;
        } else if (LeaveRequest.Status.APPROVED.name().equalsIgnoreCase(s)) {
            return LeaveRequest.Status.APPROVED;
        } else if (LeaveRequest.Status.REJECTED.name().equalsIgnoreCase(s)) {
            return LeaveRequest.Status.REJECTED;
        }

        return null;
    }
}
