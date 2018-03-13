package com.mapi.ihrd.module.employee.model.converter;

import com.mapi.ihrd.module.employee.model.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    private Logger logger = LoggerFactory.getLogger(GenderConverter.class);

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.name();
    }

    @Override
    public Gender convertToEntityAttribute(String s) {

        if (Gender.MALE.name().equalsIgnoreCase(s)) {
            return Gender.MALE;
        } else if (Gender.FEMALE.name().equalsIgnoreCase(s)) {
            return Gender.FEMALE;
        }

        return null;
    }
}
