package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.Random;

public class Doctors {
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

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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
