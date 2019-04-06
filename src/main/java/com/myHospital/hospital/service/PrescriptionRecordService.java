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

    //通过recordId查询病历记录
    Record findRecordById(String recordId);

    //通过userId查询个人以往病历和处方
    List<Record> findRecordAndPrescription(String userId,String type);

    //通过recordId查询病人处方
    List<Prescription> findPrescriptionById(String recordId);

    //更新RecordStatus
    int updateRecordStatusById(int recordStatus, String recordId);

    //查询所有已缴费病历
    List<Record> findAllRecordAndPrescriptionWithPay();
}
