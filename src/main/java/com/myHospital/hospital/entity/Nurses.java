package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Nurses {
    private String nurseId;
    private String nurseName;
    private String nurseTitle;
    private String nursePlace;
    private Timestamp nurseCreateTime;
    private Timestamp nurseUpdateTime;

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getNurseTitle() {
        return nurseTitle;
    }

    public void setNurseTitle(String nurseTitle) {
        this.nurseTitle = nurseTitle;
    }

    public String getNursePlace() {
        return nursePlace;
    }

    public void setNursePlace(String nursePlace) {
        this.nursePlace = nursePlace;
    }

    public Timestamp getNurseCreateTime() {
        return nurseCreateTime;
    }

    public void setNurseCreateTime(Timestamp nurseCreateTime) {
        this.nurseCreateTime = nurseCreateTime;
    }

    public Timestamp getNurseUpdateTime() {
        return nurseUpdateTime;
    }

    public void setNurseUpdateTime(Timestamp nurseUpdateTime) {
        this.nurseUpdateTime = nurseUpdateTime;
    }
}
