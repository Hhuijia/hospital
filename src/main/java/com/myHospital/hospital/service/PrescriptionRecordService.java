package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/17
 */
@Repository
public interface PrescriptionRecordService {

    //通过userId查询个人以往病历和处方
    List<Record> findAllRecordAndPrescription(String userId);
}
