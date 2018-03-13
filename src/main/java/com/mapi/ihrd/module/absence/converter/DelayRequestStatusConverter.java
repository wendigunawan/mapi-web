package com.mapi.ihrd.module.absence.converter;

import com.mapi.ihrd.module.absence.model.LateRequest;

import javax.persistence.AttributeConverter;

public class DelayRequestStatusConverter implements AttributeConverter<LateRequest.Status, String> {

    @Override
    public String convertToDatabaseColumn(LateRequest.Status status) {
        return status.name();
    }

    @Override
    public LateRequest.Status convertToEntityAttribute(String s) {

        if ((LateRequest.Status.NEW.name().equalsIgnoreCase(s))) {
            return LateRequest.Status.NEW;
        } else if ((LateRequest.Status.APPROVED.name().equalsIgnoreCase(s))) {
            return LateRequest.Status.APPROVED;
        } else if ((LateRequest.Status.REJECTED.name().equalsIgnoreCase(s))) {
            return LateRequest.Status.REJECTED;
        }

        return null;
    }
}
