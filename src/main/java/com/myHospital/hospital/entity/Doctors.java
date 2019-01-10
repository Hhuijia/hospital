package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.Random;

public class Doctors {
    /**
     * doctorNo	医生编号	varchar		必填	唯一代表一名医生，格式固定
     * doctorName	医生姓名	varchar		必填	真实姓名
     * doctorPwd	医生密码	varchar		必填	6-12位，数字+字母组成
     * doctorAge	医生年龄	int		必填	只允许输入整数
     * doctorSex	医生性别	int		必填	男：0/女：1
     * doctorIDNum	医生身份证号	varchar		必填	身份证号格式
     * doctorPhone	医生联系方式	varchar		必填
     * departmentNo	科室编号	varchar		必填	外键
     * doctorTitle	医生职称	varchar		必填
     * doctorProfession	医生主诊	varchar		必填	医生主要诊治领域
     * doctorMedicalServiceLife	医生从医年限	int		必填
     * doctorIntroduction	医生简介	varchar		必填
     * doctorCreateTime	医生添加时间	Timestamp		必填	根据系统时间自动生成
     * doctorUpdateTime	医生修改时间	Timestamp		必填	根据系统时间写入
     *
     * */

    private String doctorNo;
    private String doctorName;
    private String doctorPwd;
    private Integer doctorAge;
    private Integer doctorSex;
    private String doctorIDNum;
    private String doctorPhone;
    private Department department;
    private String doctorTitle;
    private String doctorProfession;
    private Integer doctorMedicalServiceLife;
    private String doctorIntroduction;
    private Timestamp doctorCreateTime;
    private Timestamp doctorUpdateTime;

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorIDNum) {
        Random random = new Random();
        String str = String.format("%04d", random.nextInt(1001));
        this.doctorNo = "DOCTOR_" + str + "_" + System.currentTimeMillis();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPwd() {
        return doctorPwd;
    }

    public void setDoctorPwd(String doctorPwd) {
        this.doctorPwd = doctorPwd;
    }

    public Integer getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(Integer doctorAge) {
        this.doctorAge = doctorAge;
    }

    public Integer getDoctorSex() {
        return doctorSex;
    }

    public void setDoctorSex(Integer doctorSex) {
        this.doctorSex = doctorSex;
    }

    public String getDoctorIDNum() {
        return doctorIDNum;
    }

    public void setDoctorIDNum(String doctorIDNum) {
        this.doctorIDNum = doctorIDNum;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getDoctorProfession() {
        return doctorProfession;
    }

    public void setDoctorProfession(String doctorProfession) {
        this.doctorProfession = doctorProfession;
    }

    public Integer getDoctorMedicalServiceLife() {
        return doctorMedicalServiceLife;
    }

    public void setDoctorMedicalServiceLife(Integer doctorMedicalServiceLife) {
        this.doctorMedicalServiceLife = doctorMedicalServiceLife;
    }

    public String getDoctorIntroduction() {
        return doctorIntroduction;
    }

    public void setDoctorIntroduction(String doctorIntroduction) {
        this.doctorIntroduction = doctorIntroduction;
    }

    public Timestamp getDoctorCreateTime() {
        return doctorCreateTime;
    }

    public void setDoctorCreateTime(Timestamp doctorCreateTime) {
        this.doctorCreateTime = doctorCreateTime;
    }

    public Timestamp getDoctorUpdateTime() {
        return doctorUpdateTime;
    }

    public void setDoctorUpdateTime(Timestamp doctorUpdateTime) {
        this.doctorUpdateTime = doctorUpdateTime;
    }
}
