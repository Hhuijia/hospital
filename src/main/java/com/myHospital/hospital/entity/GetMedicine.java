package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class GetMedicine {
    private String getMedicineId;
    private Timestamp getMedicineCreatedTime;

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
}
