package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.ScheduleDao;
import com.myHospital.hospital.dao.UsersDao;
import com.myHospital.hospital.entity.*;
import com.myHospital.hospital.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

/**
 * @author QUEENEY
 * @date 2019/1/17
 */
@Repository
public class UsersServiceImpl implements UsersService {
    private static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public int updateUserByIdNum(Users user) {
        log.info("******************updateUserByIdNum********************");
        return usersDao.updateUserByIdNum(user);
    }

    @Override
    public int updatePwdByIdNum(String userPwd, String salt, String userIDNum) {
        log.info("******************updatePwdByIdNum********************");
        return usersDao.updatePwdByIdNum(userPwd,salt,userIDNum);
    }

    @Override
    public Users findUserByIDNum(String userIDNum){
        log.info("******************findUserByIDNum********************");
        return usersDao.findUserByIDNum(userIDNum);
    }

    @Override
    public Users findUserByID(String userId) {
        log.info("******************findUserByID********************");
        return usersDao.findUserById(userId);
    }

    @Override
    public List<Role> findRoleByIDNum(String userIDNum){
        log.info("******************findRoleByIDNum********************");
        return usersDao.findRoleByIDNum(userIDNum);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String roleId){
        log.info("******************findPermissionByRoleId********************");
        return usersDao.findPermissionByRoleId(roleId);
    }

    @Override
    public List<Users> checkAllUser() {
        log.info("******************checkAllUser********************");
        return usersDao.checkAllUser();
    }

    @Override
    public void makeAppointment(Appointment appointment, String scheduleId) {
        log.info("******************makeAppointment********************");
        String strAppoint = String.format("%04d", new Random().nextInt(1001));
        appointment.setAppointmentId( "APPOINTMENT_" + strAppoint + "_" + System.currentTimeMillis());
        usersDao.makeAppointment(appointment);
        Schedule schedule = scheduleDao.findScheduleById(scheduleId);
        scheduleDao.updateRemianById(schedule.getRemain()-1, scheduleId);
    }

    @Override
    public List<Appointment> findAllAppointmentOfOneByUserId(String userId) {
        log.info("******************findAllAppointmentOfOneByUserId********************");
        return usersDao.findAllAppointmentOfOneByUserId(userId);
    }

    @Override
    public void updateStatusById(String appointmentId, int appointmentStatus) {
        log.info("******************updateStatusById********************");
        usersDao.updateStatusById(appointmentId,appointmentStatus);
    }
}
