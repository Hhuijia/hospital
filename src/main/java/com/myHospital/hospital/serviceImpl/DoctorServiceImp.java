package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.DoctorsDao;
import com.myHospital.hospital.dao.ScheduleDao;
import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Schedule;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.DoctorService;
import com.myHospital.hospital.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author QUEENEY
 * @date 2019/3/9
 */
@Repository
public class DoctorServiceImp implements DoctorService {
    private static final Logger log = LoggerFactory.getLogger(DoctorServiceImp.class);

    @Autowired
    private DoctorsDao doctorsDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public Doctors findDoctorByUserId(String userId) {
        log.info("******************findDoctorByUserId********************");
        return doctorsDao.findDoctorByUserId(userId);
    }

    @Override
    public Doctors findDoctorById(String doctorId) {
        log.info("******************findDoctorById********************");
        return doctorsDao.findDoctorById(doctorId);
    }

    @Override
    public List<Doctors> findDoctorInSameDepartment(String departmentName) {
        log.info("******************findDoctorInSameDepartment********************");
        return doctorsDao.findDoctorInSameDepartment(departmentName);
    }
//
//    @Override
//    public List<String> findDoctorIdInSameDepartment(String departmentName) {
//        return doctorsDao.findDoctorIdInSameDepartment(departmentName);
//    }

    @Override
    public List<Doctors> findDoctorToday(String departmentName, String currentDate) {
        log.info("******************findDoctorToday********************");
        log.info("*******[{}]-[{}]******",departmentName,currentDate);
        return doctorsDao.findDoctorToday(departmentName,currentDate);
    }

}
