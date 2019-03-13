package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Appointment {
    private String appointmentId;
    private String emergencyContact;
    private String emergContPhone;
    private Timestamp appointmentTime;
    private Timestamp appointmentCreatedTime;
    private String userId;
    private String doctorId;
    private String departmentId;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergContPhone() {
        return emergContPhone;
    }

    public void setEmergContPhone(String emergContPhone) {
        this.emergContPhone = emergContPhone;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Timestamp getAppointmentCreatedTime() {
        return appointmentCreatedTime;
    }

    public void setAppointmentCreatedTime(Timestamp appointmentCreatedTime) {
        this.appointmentCreatedTime = appointmentCreatedTime;
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

    @Override
    public String toString() {
        String str = this.appointmentId+","+this.userId+","+this.doctorId+","+this.departmentId+","+
                this.emergencyContact+","+this.emergContPhone+","+
                this.appointmentTime+","+this.appointmentCreatedTime;
        return str;
    }
}
