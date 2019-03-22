package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Record {
    private String recordId;
    private String diagnosticResult;
    private String symptom;
    private Timestamp recordCreatedTime;
    private String userId;
    private String doctorId;

    private List<Prescription> prescriptions;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getDiagnosticResult() {
        return diagnosticResult;
    }

    public void setDiagnosticResult(String diagnosticResult) {
        this.diagnosticResult = diagnosticResult;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public Timestamp getRecordCreatedTime() {
        return recordCreatedTime;
    }

    public void setRecordCreatedTime(Timestamp recordCreatedTime) {
        this.recordCreatedTime = recordCreatedTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
