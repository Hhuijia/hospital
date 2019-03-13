package com.myHospital.hospital.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Pay {
    private String payId;
    private BigDecimal payCount;
    private Integer payWay;
    private Timestamp payTime;
    private Timestamp payCreatedTime;
    private String userId;
    private String nurseId;

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

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getPayCreatedTime() {
        return payCreatedTime;
    }

    public void setPayCreatedTime(Timestamp payCreatedTime) {
        this.payCreatedTime = payCreatedTime;
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
}
