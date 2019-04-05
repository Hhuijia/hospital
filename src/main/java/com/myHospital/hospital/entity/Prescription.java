package com.myHospital.hospital.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Prescription {
    private String prescriptionId;
    private int prescriptionCount;//数量
    private String prescriptionUnit;//单位
    private BigDecimal dosageEachTime;//单次服用剂量
    private String prescriptionDosage;//服用次数
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

    public int getPrescriptionCount() {
        return prescriptionCount;
    }

    public void setPrescriptionCount(int prescriptionCount) {
        this.prescriptionCount = prescriptionCount;
    }

    public String getPrescriptionUnit() {
        return prescriptionUnit;
    }

    public void setPrescriptionUnit(String prescriptionUnit) {
        this.prescriptionUnit = prescriptionUnit;
    }

    public BigDecimal getDosageEachTime() {
        return dosageEachTime;
    }

    public void setDosageEachTime(BigDecimal dosageEachTime) {
        this.dosageEachTime = dosageEachTime;
    }

    public String getPrescriptionDosage() {
        return prescriptionDosage;
    }

    public void setPrescriptionDosage(String prescriptionDosage) {
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
