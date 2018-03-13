package com.mapi.ihrd.module.employee.model;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constant.TABLE_EDUCATION)
public class Education extends BaseModel {

    @NotNull
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
