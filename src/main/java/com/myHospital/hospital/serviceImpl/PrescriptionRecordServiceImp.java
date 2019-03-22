package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.PrescriptionRecordDao;
import com.myHospital.hospital.entity.Prescription;
import com.myHospital.hospital.entity.Record;
import com.myHospital.hospital.service.PrescriptionRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

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
    public void addRecordAndPrescription(List<Prescription> prescriptions, Record record) {
        log.info("******************addRecordAndPrescription********************");
        String strRecord = String.format("%04d", new Random().nextInt(1001));
        record.setRecordId( "RECORD" + strRecord + "_" + System.currentTimeMillis());
        prescriptionRecordDao.addRecord(record);
        for (Prescription prescription : prescriptions){
            String strPres = String.format("%04d", new Random().nextInt(1001));
            prescription.setPrescriptionId( "PRESCRIPTION_" + strPres + "_" + System.currentTimeMillis());
            prescription.setRecordId(record.getRecordId());
            prescriptionRecordDao.addPrescription(prescription);
        }
    }

    @Override
    public List<Record> findAllRecordAndPrescription(String userId) {
        log.info("******************findAllRecordAndPrescription********************");
        return prescriptionRecordDao.findAllRecordAndPrescription(userId);
    }
}
