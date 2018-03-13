package com.mapi.ihrd.module.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.module.employee.model.converter.EmployeeStatusConverter;
import com.mapi.ihrd.module.employee.model.converter.GenderConverter;
import org.hibernate.validator.constraints.Email;


import javax.jdo.annotations.Join;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name = Constant.TABLE_NAME_EMPLOYEE)
public class Employee extends BaseModel implements Serializable {

    @Column(name = "nik", unique = true)
    private String nik;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_place")
    private String birthPlace;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone_number1")
    private String phoneNumber1;

    @Column(name = "phone_number2")
    private String phoneNumber2;

    @Column(name = "npwp_no")
    private String npwp;

    @Email
    @Column(name = "email")
    private String email;

    @Convert(converter = GenderConverter.class)
    @Column(name = "gender")
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "join_date")
    private Date joinDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_department_id")
    private Department currentDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_job_id")
    private Job currentJob;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_manager_id", insertable = false, updatable = false)
    private Employee currentManager;

    @Convert(converter = EmployeeStatusConverter.class)
    @Column(name = "status")
    private Status status;

    @Column(name = "address")
    private String address;

    @Column(name = "current_salary")
    private String currentSalary;

    @Column(name = "picture")
    private String picture;

    //TODO untuk next fitur. perusahaan skala nasional dengan banyak cabang
    @Transient
    private Branch branch;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<EmployeeDocument> employeeDocuments;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<EmployeeSkill> employeeSkills;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<EmployeeCertificate> employeeCertificates;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Department getCurrentDepartment() {
        return currentDepartment;
    }

    public void setCurrentDepartment(Department currentDepartment) {
        this.currentDepartment = currentDepartment;
    }

    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(String currentSalary) {
        this.currentSalary = currentSalary;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Collection<EmployeeDocument> getEmployeeDocuments() {
        return employeeDocuments;
    }

    public void setEmployeeDocuments(Collection<EmployeeDocument> employeeDocuments) {
        this.employeeDocuments = employeeDocuments;
    }

    public Collection<EmployeeSkill> getEmployeeSkills() {
        return employeeSkills;
    }

    public void setEmployeeSkills(Collection<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }

    public Collection<EmployeeCertificate> getEmployeeCertificates() {
        return employeeCertificates;
    }

    public void setEmployeeCertificates(Collection<EmployeeCertificate> employeeCertificates) {
        this.employeeCertificates = employeeCertificates;
    }

    public Employee getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(Employee currentManager) {
        this.currentManager = currentManager;
    }

    public enum Status {

        ACTIVE("active"), LEAVE("leave"), RESIGN("resign");

        private Status(String val) {

        }

    }


}
