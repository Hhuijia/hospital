package com.myHospital.hospital.entity;

import java.sql.Timestamp;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Record {
    private String recordId;
    private String recordContent;
    private Timestamp recordCreatedTime;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public Timestamp getRecordCreatedTime() {
        return recordCreatedTime;
    }

    public void setRecordCreatedTime(Timestamp recordCreatedTime) {
        this.recordCreatedTime = recordCreatedTime;
    }
}
