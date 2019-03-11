package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Department {
    private String departmentId;
    private String departmentName;
    private String departmentSystem;
    private String departmentInfo;
    private String departmentSymptom;
    private Timestamp departmentCreateTime;
    private Timestamp departmentUpdateTime;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentSystem() {
        return departmentSystem;
    }

    public void setDepartmentSystem(String departmentSystem) {
        this.departmentSystem = departmentSystem;
    }

    public String getDepartmentInfo() {
        return departmentInfo;
    }

    public void setDepartmentInfo(String departmentInfo) {
        this.departmentInfo = departmentInfo;
    }

    public String getDepartmentSymptom() {
        return departmentSymptom;
    }

    public void setDepartmentSymptom(String departmentSymptom) {
        this.departmentSymptom = departmentSymptom;
    }

    public Timestamp getDepartmentCreateTime() {
        return departmentCreateTime;
    }

    public void setDepartmentCreateTime(Timestamp departmentCreateTime) {
        this.departmentCreateTime = departmentCreateTime;
    }

    public Timestamp getDepartmentUpdateTime() {
        return departmentUpdateTime;
    }

    public void setDepartmentUpdateTime(Timestamp departmentUpdateTime) {
        this.departmentUpdateTime = departmentUpdateTime;
    }
}
