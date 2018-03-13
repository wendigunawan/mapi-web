package com.mapi.ihrd.module.aauth.model;

import javax.persistence.*;

import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.module.employee.model.Employee;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = Constant.TABLE_NAME_USER)
public class User extends UserBaseModel {

    @Column(name = "ref_id", insertable = false, updatable = false)
    private String refId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = Constant.TABLE_NAME_USER_ROLE,
            joinColumns = {
                    @JoinColumn(name = "user_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", nullable = false, updatable = false)
            })
    private Collection<Role> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_id")
    private Employee employee;

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public enum Type {
        NONE("none"), EMPLOYEE("employee");

        private Type(String val) {

        }


    }
}
