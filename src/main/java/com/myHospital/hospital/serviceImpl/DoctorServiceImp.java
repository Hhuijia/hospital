package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.DoctorsDao;
import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.DoctorService;
import com.myHospital.hospital.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Override
    public Doctors findDoctorByUserId(String userId) {
        log.info("******************findDoctorByUserId********************");
        return doctorsDao.findDoctorByUserId(userId);
    }

    @Override
    public List<Doctors> findDoctorInSameDepartment(String departmentName) {
        log.info("******************findDoctorInSameDepartment********************");
        return doctorsDao.findDoctorInSameDepartment(departmentName);
    }

}
