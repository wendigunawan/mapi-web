package com.mapi.ihrd.module.performance.model;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constant.TABLE_NAME_PERFORM_COMPONENT)
public class Component extends BaseModel {
    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
