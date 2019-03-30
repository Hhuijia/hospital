package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/3/30
 */
public class Schedule {
    private String scheduleId;
    private Timestamp workTime;
    private Timestamp workDate;
    private String doctorId;
    private Timestamp scheduleCreateTime;
    private Timestamp scheduleUpdateTime;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Timestamp getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Timestamp workTime) {
        this.workTime = workTime;
    }

    public Timestamp getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Timestamp workDate) {
        this.workDate = workDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Timestamp getScheduleCreateTime() {
        return scheduleCreateTime;
    }

    public void setScheduleCreateTime(Timestamp scheduleCreateTime) {
        this.scheduleCreateTime = scheduleCreateTime;
    }

    public Timestamp getScheduleUpdateTime() {
        return scheduleUpdateTime;
    }

    public void setScheduleUpdateTime(Timestamp scheduleUpdateTime) {
        this.scheduleUpdateTime = scheduleUpdateTime;
    }
}
