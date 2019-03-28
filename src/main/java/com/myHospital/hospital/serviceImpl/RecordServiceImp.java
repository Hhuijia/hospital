package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.DoctorsDao;
import com.myHospital.hospital.dao.RecordDao;
import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.GetMedicine;
import com.myHospital.hospital.entity.Pay;
import com.myHospital.hospital.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 3/13/2019
 */
@Repository
public class RecordServiceImp implements RecordService {
    private static final Logger log = LoggerFactory.getLogger(RecordServiceImp.class);

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private DoctorsDao doctorsDao;

    @Override
    public List<Appointment> findAllAppointment() {
        log.info("******************findAllAppointment********************");
        return recordDao.findAllAppointment();
    }

    @Override
    public List<Pay> findAllPay() {
        log.info("******************findAllPay********************");
        return recordDao.findAllPay();
    }

    @Override
    public List<GetMedicine> findAllGetMedicine() {
        log.info("******************findAllGetMedicine********************");
        return recordDao.findAllGetMedicine();
    }

    @Override
    public List<String> findColumnName(String tableName) {
        log.info("******************findColumnName********************");
        return recordDao.findColumnName(tableName);
    }

    @Override
    public List<Appointment> findTodayAppointment(String doctorId) {
        log.info("******************findTodayAppointment********************");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDateTime = simpleDateFormat.format(new Date());
        log.info("*******[{}]-[{}]******",doctorId,currentDateTime);
        return recordDao.findTodayAppointment(doctorId, currentDateTime);
    }
}
