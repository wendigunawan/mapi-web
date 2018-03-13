package com.mapi.ihrd.module.employee.model;


import com.mapi.ihrd.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mst_skill")
public class Skill extends BaseModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
