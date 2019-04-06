package com.myHospital.hospital.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Pay {
    private String payId;
    private BigDecimal payCount;//总金额
    private Timestamp payCreatedTime;
    private String recordId;
    private String userId;
    private String nurseId;

    private Record record;
    private String userName;

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public BigDecimal getPayCount() {
        return payCount;
    }

    public void setPayCount(BigDecimal payCount) {
        this.payCount = payCount;
    }

    public Timestamp getPayCreatedTime() {
        return payCreatedTime;
    }

    public void setPayCreatedTime(Timestamp payCreatedTime) {
        this.payCreatedTime = payCreatedTime;
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

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
