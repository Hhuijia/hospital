package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Appointment {
    private String appointmentId;
    private String appointmentTime;
    private Timestamp appointmentCreatedTime;
    private int appointmentStatus;
    private String userId;
    private String doctorId;
    private String departmentId;

    private Users users;
    private Doctors doctors;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Timestamp getAppointmentCreatedTime() {
        return appointmentCreatedTime;
    }

    public void setAppointmentCreatedTime(Timestamp appointmentCreatedTime) {
        this.appointmentCreatedTime = appointmentCreatedTime;
    }

    public int getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(int appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        String str = this.appointmentId+","+this.userId+","+this.doctorId+","+this.departmentId+","+
                this.appointmentTime+","+this.appointmentCreatedTime;
        return str;
    }
}
