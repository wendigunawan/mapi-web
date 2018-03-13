package com.mapi.ihrd.module.employee.model;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Constant.TABLE_NAME_EMPLOYMENT_STATUS)
public class EmploymentStatus extends BaseModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
