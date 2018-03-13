package com.mapi.ihrd.module.health.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = Constant.TABLE_NAME_DOCTOR)
public class Doctor extends BaseModel {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "specialist", nullable = false)
    private String specialist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }
}
