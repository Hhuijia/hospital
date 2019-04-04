package com.myHospital.hospital.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Medicine {
    private String medicineId;
    private String medicineName;
    private String medicineUnit;//单位
    private BigDecimal medicinePrice;//单价
    private String medicineResidual;//库存
    private String medicineType;//类型 ag:妇科用药
    private String medicineDosage;//规格 ag:0.5g*24粒
    private String companyName;//公司名称
    private Timestamp medicineCreateTime;
    private Timestamp medicineUpdateTime;

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineUnit() {
        return medicineUnit;
    }

    public void setMedicineUnit(String medicineUnit) {
        this.medicineUnit = medicineUnit;
    }

    public BigDecimal getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(BigDecimal medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public String getMedicineResidual() {
        return medicineResidual;
    }

    public void setMedicineResidual(String medicineResidual) {
        this.medicineResidual = medicineResidual;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(String medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Timestamp getMedicineCreateTime() {
        return medicineCreateTime;
    }

    public void setMedicineCreateTime(Timestamp medicineCreateTime) {
        this.medicineCreateTime = medicineCreateTime;
    }

    public Timestamp getMedicineUpdateTime() {
        return medicineUpdateTime;
    }

    public void setMedicineUpdateTime(Timestamp medicineUpdateTime) {
        this.medicineUpdateTime = medicineUpdateTime;
    }
}
