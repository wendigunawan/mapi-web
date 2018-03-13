package com.mapi.ihrd.module.aauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.config.Constant;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = Constant.TABLE_NAME_ROLE)
public class Role extends BaseModel {

    @NotNull
    @NotEmpty
    @Column(name = "code")
    private String code;

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = Constant.TABLE_NAME_ROLE_PERMISSION,
            joinColumns = {
                    @JoinColumn(name = "permission_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", nullable = false, updatable = false)
            })
    private Collection<Permission> permissions;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }
}
