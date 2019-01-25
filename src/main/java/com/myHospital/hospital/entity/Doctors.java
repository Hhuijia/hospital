package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.Random;

public class Doctors {
    /**
     * doctorId	医生ID	varchar		必填	唯一代表一名医生，格式固定
     * departmentId	科室ID	varchar		必填	外键
     * doctorName	医生姓名	varchar		必填
     * doctorTitle	医生职称	varchar		必填
     * doctorProfession	医生主诊	varchar		必填	医生主要诊治领域
     * doctorMedicalServiceLife	医生从医年限	int		必填
     * doctorIntroduction	医生简介	varchar		必填
     * doctorCreateTime	医生添加时间	date		必填	根据系统时间自动生成
     * doctorUpdateTime	医生修改时间	timestamp		必填	根据系统时间写入
     *
     * */

    private String doctorId;
    private String doctorName;
    private String doctorTitle;
    private String doctorProfession;
    private Integer doctorMedicalServiceLife;
    private String doctorIntroduction;
    private Timestamp doctorCreateTime;
    private Timestamp doctorUpdateTime;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId() {
        Random random = new Random();
        String str = String.format("%04d", random.nextInt(1001));
        this.doctorId = "DOCTOR_" + str + "_" + System.currentTimeMillis();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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
