package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class GetMedicine {
    private String getMedicineId;
    private Timestamp getMedicineCreatedTime;
    private String recordId;
    private String userId;
    private String nurseId;

    private Users users;
    private String nurseName;

    public String getGetMedicineId() {
        return getMedicineId;
    }

    public void setGetMedicineId(String getMedicineId) {
        this.getMedicineId = getMedicineId;
    }

    public Timestamp getGetMedicineCreatedTime() {
        return getMedicineCreatedTime;
    }

    public void setGetMedicineCreatedTime(Timestamp getMedicineCreatedTime) {
        this.getMedicineCreatedTime = getMedicineCreatedTime;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }
}
