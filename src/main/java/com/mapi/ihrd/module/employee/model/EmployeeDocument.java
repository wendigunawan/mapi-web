package com.mapi.ihrd.module.employee.model;

import com.mapi.ihrd.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "mst_employee_document")
public class EmployeeDocument extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
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
