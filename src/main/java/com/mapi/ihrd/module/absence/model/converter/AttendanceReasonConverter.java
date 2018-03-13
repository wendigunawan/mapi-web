package com.mapi.ihrd.module.absence.model.converter;

import com.mapi.ihrd.module.absence.model.Attendance;

import javax.persistence.AttributeConverter;

public class AttendanceReasonConverter implements AttributeConverter<Attendance.Reason, String> {

    @Override
    public String convertToDatabaseColumn(Attendance.Reason reasonType) {
        if(reasonType != null){
            return reasonType.name();
        }

        return null;
    }

    @Override
    public Attendance.Reason convertToEntityAttribute(String s) {

        if (Attendance.Reason.LEAVE.name().equalsIgnoreCase(s)) {
            return Attendance.Reason.LEAVE;
        } else if (Attendance.Reason.SICK.name().equalsIgnoreCase(s)) {
            return Attendance.Reason.SICK;
        } else if (Attendance.Reason.ALPHA.name().equalsIgnoreCase(s)) {
            return Attendance.Reason.ALPHA;
        }

        return null;
    }
}
