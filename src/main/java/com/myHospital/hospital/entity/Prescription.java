package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Prescription {
    private String prescriptionId;
    private int prescriptionDosage;
    private String prescriptionUsage;
    private Timestamp prescriptionCreatedTime;
    private String recordId;
    private String medicineId;

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public int getPrescriptionDosage() {
        return prescriptionDosage;
    }

    public void setPrescriptionDosage(int prescriptionDosage) {
        this.prescriptionDosage = prescriptionDosage;
    }

    public String getPrescriptionUsage() {
        return prescriptionUsage;
    }

    public void setPrescriptionUsage(String prescriptionUsage) {
        this.prescriptionUsage = prescriptionUsage;
    }

    public Timestamp getPrescriptionCreatedTime() {
        return prescriptionCreatedTime;
    }

    public void setPrescriptionCreatedTime(Timestamp prescriptionCreatedTime) {
        this.prescriptionCreatedTime = prescriptionCreatedTime;
    }
}
