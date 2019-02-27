package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Prescription {
    private String prescriptionId;
    private String prescriptionContent;
    private Timestamp prescriptionCreatedTime;

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionContent() {
        return prescriptionContent;
    }

    public void setPrescriptionContent(String prescriptionContent) {
        this.prescriptionContent = prescriptionContent;
    }

    public Timestamp getPrescriptionCreatedTime() {
        return prescriptionCreatedTime;
    }

    public void setPrescriptionCreatedTime(Timestamp prescriptionCreatedTime) {
        this.prescriptionCreatedTime = prescriptionCreatedTime;
    }
}
