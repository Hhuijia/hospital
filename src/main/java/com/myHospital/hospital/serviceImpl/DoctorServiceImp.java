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

    @Autowired
    private UsersService usersService;

    @Override
    public void addDoctor(Users users, Doctors doctors, String userIDNum, String departmentName) {
        log.info("******************addDoctor********************");
        users.setUserPwd("huanghuijia");
        if (usersService.findUserByIDNum(userIDNum)==null){
            usersService.addUsers(users);
            String userId = usersService.findUserIdByIDNum(userIDNum);
            String str = String.format("%04d", new Random().nextInt(1001));
            doctors.setDoctorId( "DOCTOR_" + str + "_" + System.currentTimeMillis());
            doctors.setUserId(userId);
            doctors.setDepartmentName(departmentName);
            doctorsDao.addDoctor(doctors);
        }else {
            log.info("******************该用户存在********************");
        }
    }

    @Override
    public List<Doctors> findAllDoctor() {
        log.info("******************findAllDoctor********************");
        return doctorsDao.findAllDoctor();
    }

    @Override
    public void deleteDoctor(String doctorId) {
        log.info("******************deleteDoctorById********************");
        String userId = doctorsDao.findUserIdById(doctorId);
        usersService.deleteUserById(userId);
        doctorsDao.deleteDoctorById(doctorId);
    }


}
