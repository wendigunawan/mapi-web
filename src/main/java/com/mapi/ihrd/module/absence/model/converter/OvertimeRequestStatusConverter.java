package com.mapi.ihrd.module.absence.model.converter;

import com.mapi.ihrd.module.absence.model.OvertimeRequest;

import javax.persistence.AttributeConverter;

public class OvertimeRequestStatusConverter implements AttributeConverter<OvertimeRequest.Status, String> {
    @Override
    public String convertToDatabaseColumn(OvertimeRequest.Status status) {
        return status.name();
    }

    @Override
    public OvertimeRequest.Status convertToEntityAttribute(String s) {


        if (OvertimeRequest.Status.NEW.name().equalsIgnoreCase(s)) {
            return OvertimeRequest.Status.NEW;
        } else if (OvertimeRequest.Status.APPROVED.name().equalsIgnoreCase(s)) {
            return OvertimeRequest.Status.APPROVED;
        } else if (OvertimeRequest.Status.REJECTED.name().equalsIgnoreCase(s)) {
            return OvertimeRequest.Status.REJECTED;
        }

        return null;
    }
}
