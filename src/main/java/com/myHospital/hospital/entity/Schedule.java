package com.myHospital.hospital.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/3/30
 */
public class Schedule {
    private String scheduleId;
    private int workTime;
    private Date workDate;
    private int remain;
    private String doctorId;
    private Timestamp scheduleCreateTime;
    private Timestamp scheduleUpdateTime;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
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
