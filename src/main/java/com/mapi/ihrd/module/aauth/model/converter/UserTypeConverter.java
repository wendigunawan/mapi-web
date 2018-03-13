package com.mapi.ihrd.module.aauth.model.converter;

import com.mapi.ihrd.module.aauth.model.User;

import javax.persistence.AttributeConverter;

public class UserTypeConverter implements AttributeConverter<User.Type, String> {

    @Override
    public String convertToDatabaseColumn(User.Type type) {
        return type.name();
    }

    @Override
    public User.Type convertToEntityAttribute(String s) {

        if(User.Type.NONE.name().equals(s)){
            return User.Type.NONE;
        }else if(User.Type.EMPLOYEE.name().equals(s)){
            return User.Type.EMPLOYEE;
        }

        return null;
    }
}
