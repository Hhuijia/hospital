package com.myHospital.hospital.entity;

public class Department {
    /**
     * departmentNo	科室编号	varchar		必填	唯一代表一个科室，格式固定
     * departmentName	科室名称	varchar		必填
     * departmentSystem	所属系统	varchar		必填	内科，外科，妇儿科，五官科
     * departmentInfo	科室简介	varchar		必填
     * departmentSymptom	主要表现症状	varchar		必填
     *
     * */
    private String departmentNo;
    private String departmentName;
    private String departmentSystem;
    private String departmentInfo;
    private String departmentSymptom;

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = "DEPARTMENT_" + departmentNo + "_" + System.currentTimeMillis();
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
}
