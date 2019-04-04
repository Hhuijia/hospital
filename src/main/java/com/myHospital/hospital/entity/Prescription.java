package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Prescription {
    private String prescriptionId;
    private int medicineCount;//数量
    private String medicineUnit;//单位
    private int prescriptionDosage;//剂量
    private String prescriptionUsage;//用法 ag:口服
    private Timestamp prescriptionCreatedTime;
    private String recordId;
    private String medicineId;

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getMedicineCount() {
        return medicineCount;
    }

    public void setMedicineCount(int medicineCount) {
        this.medicineCount = medicineCount;
    }

    public String getMedicineUnit() {
        return medicineUnit;
    }

    public void setMedicineUnit(String medicineUnit) {
        this.medicineUnit = medicineUnit;
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
}
