package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.RecordDao;
import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.GetMedicine;
import com.myHospital.hospital.entity.Pay;
import com.myHospital.hospital.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 3/13/2019
 */
@Repository
public class RecordServiceImp implements RecordService {

    @Autowired
    private RecordDao recordDao;

    @Override
    public List<Appointment> findAllAppointment() {
        return recordDao.findAllAppointment();
    }

    @Override
    public List<Pay> findAllPay() {
        return recordDao.findAllPay();
    }

    @Override
    public List<GetMedicine> findAllGetMedicine() {
        return recordDao.findAllGetMedicine();
    }

    @Override
    public List<String> findColumnName(String tableName) {
        return recordDao.findColumnName(tableName);
    }
}
