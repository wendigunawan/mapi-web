package com.mapi.ihrd.module.performance.model;

import com.mapi.ihrd.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class PerformanceBaseModel extends BaseModel{

    @Temporal(TemporalType.DATE)
    @Column(name = "performance_date")
    private Date performanceDate;

    @Column(name = "value")
    private int performanceValue;

    public Date getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(Date performanceDate) {
        this.performanceDate = performanceDate;
    }

    public int getPerformanceValue() {
        return performanceValue;
    }

    public void setPerformanceValue(int performanceValue) {
        this.performanceValue = performanceValue;
    }
}
