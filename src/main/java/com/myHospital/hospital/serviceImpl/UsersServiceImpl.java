package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.UsersDao;
import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.Users;
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

    @Override
    public int updateUserByIdNum(Users user) {
        log.info("******************updateUserByIdNum********************");
        return usersDao.updateUserByIdNum(user);
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
    public void makeAppointment(Appointment appointment) {
        log.info("******************makeAppointment********************");
        String strAppoint = String.format("%04d", new Random().nextInt(1001));
        appointment.setAppointmentId( "APPOINTMENT_" + strAppoint + "_" + System.currentTimeMillis());
        usersDao.makeAppointment(appointment);
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
