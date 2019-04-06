package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.MedicineDepartmentDao;
import com.myHospital.hospital.dao.PrescriptionRecordDao;
import com.myHospital.hospital.entity.Medicine;
import com.myHospital.hospital.entity.Prescription;
import com.myHospital.hospital.entity.Record;
import com.myHospital.hospital.service.PrescriptionRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    @Autowired
    private MedicineDepartmentDao medicineDepartmentDao;

    @Override
    public int addRecordAndPrescription(List<Prescription> prescriptions, Record record) {
        log.info("******************addRecordAndPrescription********************");
        String strRecord = String.format("%04d", new Random().nextInt(1001));
        record.setRecordId( "RECORD_" + strRecord + "_" + System.currentTimeMillis());
        int addRecordSuccess = prescriptionRecordDao.addRecord(record);
        if (addRecordSuccess == 1){
            for (Prescription prescription : prescriptions){
                String strPres = String.format("%04d", new Random().nextInt(1001));
                prescription.setPrescriptionId( "PRESCRIPTION_" + strPres + "_" + System.currentTimeMillis());
                prescription.setRecordId(record.getRecordId());
                int addPrescriptionSuccess = prescriptionRecordDao.addPrescription(prescription);
                if (addPrescriptionSuccess == 0){
                    return 0;
                }else {
                    Medicine medicine = medicineDepartmentDao.findMedicineById(prescription.getMedicineId());
                    int medicineResidual = medicine.getMedicineResidual();
                    int residualAfterUpdate = medicineResidual - prescription.getPrescriptionCount();
                    medicineDepartmentDao.updateMedicineResidual(residualAfterUpdate,prescription.getMedicineId());
                }
            }
        }else {
            return 0;
        }
        return 1;
    }

    @Override
    public Record findRecordById(String recordId) {
        log.info("******************findAllRecordAndPrescription********************");
        return prescriptionRecordDao.findRecordById(recordId);
    }

    @Override
    public List<Record> findRecordAndPrescription(String userId,String type) {
        log.info("******************findAllRecordAndPrescription********************");
        List<Record> records = new ArrayList<>();
        switch (type){
            case "all":
                records =  prescriptionRecordDao.findAllRecordAndPrescription(userId);
            case "withoutPay":
                records = prescriptionRecordDao.findRecordAndPrescriptionWithoutPay(userId);
        }
        return records;
    }

    @Override
    public List<Prescription> findPrescriptionById(String recordId) {
        log.info("******************updateRecordStatusById********************");
        return prescriptionRecordDao.findPrescriptionById(recordId);
    }

    @Override
    public int updateRecordStatusById(int recordStatus, String recordId) {
        log.info("******************updateRecordStatusById********************");
        return prescriptionRecordDao.updateRecordStatusById(recordStatus,recordId);
    }

    @Override
    public List<Record> findAllRecordAndPrescriptionWithPay() {
        log.info("******************findAllRecordAndPrescriptionWithoutPay********************");
        return prescriptionRecordDao.findAllRecordAndPrescriptionWithPay();
    }
}
