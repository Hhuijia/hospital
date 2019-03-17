package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.PrescriptionRecordDao;
import com.myHospital.hospital.entity.Record;
import com.myHospital.hospital.service.PrescriptionRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/17
 */
@Repository
public class PrescriptionRecordServiceImp implements PrescriptionRecordService {
    private static final Logger log = LoggerFactory.getLogger(PrescriptionRecordServiceImp.class);

    @Autowired
    private PrescriptionRecordDao prescriptionRecordDao;

    @Override
    public List<Record> findAllRecordAndPrescription(String userId) {
        log.info("******************findAllRecordAndPrescription********************");
        return prescriptionRecordDao.findAllRecordAndPrescription(userId);
    }
}
