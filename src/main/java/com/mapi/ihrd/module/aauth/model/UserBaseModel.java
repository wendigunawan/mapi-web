package com.mapi.ihrd.module.aauth.model;

import com.mapi.ihrd.module.aauth.model.converter.UserTypeConverter;
import com.mapi.ihrd.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UserBaseModel extends BaseModel {

    @Column(name = "username")
    private String username;

    @Column(name = "passwd")
    private String passwd;

    @Column(name = "full_name")
    private String fullName;

    @Convert(converter = UserTypeConverter.class)
    @Column(name = "type")
    private User.Type type;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public User.Type getType() {
        return type;
    }

    public void setType(User.Type type) {
        this.type = type;
    }
}
