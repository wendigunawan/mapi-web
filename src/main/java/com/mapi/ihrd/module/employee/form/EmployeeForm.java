package com.mapi.ihrd.module.employee.form;

import com.mapi.ihrd.module.employee.model.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeForm implements Serializable{

    private String id;

    @NotNull
    @NotEmpty
    private String nik;

    @NotNull
    @NotEmpty
    private String fullName;

    private String birthPlace;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    private String phoneNumber1;
    private String phoneNumber2;
    private String email;
    private String address;
    private Gender gender;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date joinDate;
    private Department currentDepartment;
    private Job currentJob;
    private Employee currentManager;
    private String npwp;
    private Employee.Status status;
    private String currentSalary;
    private String picture;
    private List<EmployeeEmergency> emergencies;
    private List<EmployeeDependant> dependants;
    private MultipartFile file;

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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

    public Employee.Status getStatus() {
        return status;
    }

    public void setStatus(Employee.Status status) {
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<EmployeeEmergency> getEmergencies() {
        return emergencies;
    }

    public void addEmergency(EmployeeEmergency employeeEmergency){
        if(emergencies == null){
            emergencies = new ArrayList<>();
        }

        emergencies.add(employeeEmergency);
    }

    public void addDependant(EmployeeDependant employeeDependant){
        if(dependants == null){
            dependants = new ArrayList<>();
        }

        dependants.add(employeeDependant);
    }

    public void setEmergencies(List<EmployeeEmergency> emergencies) {
        this.emergencies = emergencies;
    }

    public List<EmployeeDependant> getDependants() {
        return dependants;
    }

    public void setDependants(List<EmployeeDependant> dependants) {
        this.dependants = dependants;
    }

    public Employee getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(Employee currentManager) {
        this.currentManager = currentManager;
    }
}

