package com.mapi.ihrd.module.employee.model;


import com.mapi.ihrd.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mst_employee_certificate")
public class EmployeeCertificate extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    @Column(name = "certificate_date")
    private Date certificateDate;

    private String description;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_name_origin")
    private String fileNameOrigin;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Date getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(Date certificateDate) {
        this.certificateDate = certificateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameOrigin() {
        return fileNameOrigin;
    }

    public void setFileNameOrigin(String fileNameOrigin) {
        this.fileNameOrigin = fileNameOrigin;
    }
}
