package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Prescription;
import com.myHospital.hospital.entity.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/17
 */
@Repository
public interface PrescriptionRecordService {

    //添加病历和处方
    int addRecordAndPrescription(List<Prescription> prescriptions, Record record);

    //通过userId查询个人以往病历和处方
    List<Record> findAllRecordAndPrescription(String userId);
}
