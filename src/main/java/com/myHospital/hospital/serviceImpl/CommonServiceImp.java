package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.AdminsDao;
import com.myHospital.hospital.dao.DoctorsDao;
import com.myHospital.hospital.dao.NursesDao;
import com.myHospital.hospital.dao.UsersDao;
import com.myHospital.hospital.entity.Admins;
import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Nurses;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.CommonService;
import com.myHospital.hospital.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

/**
 * @author QUEENEY
 * @date 2019/3/10
 */
@Repository
public class CommonServiceImp implements CommonService {
    private static final Logger log = LoggerFactory.getLogger(CommonServiceImp.class);

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DoctorsDao doctorsDao;

    @Autowired
    private NursesDao nursesDao;

    @Autowired
    private AdminsDao adminsDao;

    @Override
    public void add(Users users, Object o, String type) {
        log.info("******************add********************");
        if (usersDao.findUserByIDNum(users.getUserIDNum()) == null){
            String strUser = String.format("%04d", new Random().nextInt(1001));
            users.setUserId( "USER_" + strUser + "_" + System.currentTimeMillis());
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(users);
            usersDao.addUser(users);
            String userId = usersDao.findUserIdByIDNum(users.getUserIDNum());
            switch (type) {
                case "doctor":
                    log.info("******************addDoctor********************");
                    Doctors doctors = (Doctors) o;
                    String strDoctor = String.format("%04d", new Random().nextInt(1001));
                    doctors.setDoctorId("DOCTOR_" + strDoctor + "_" + System.currentTimeMillis());
                    doctors.setUserId(userId);
                    doctorsDao.addDoctor(doctors);
                    break;
                case "nurse":
                    log.info("******************addNurse********************");
                    Nurses nurses = (Nurses) o;
                    String strNurse = String.format("%04d", new Random().nextInt(1001));
                    nurses.setNurseId( "NURSE_" + strNurse + "_" + System.currentTimeMillis());
                    nurses.setUserId(userId);
                    nursesDao.addNurse(nurses);
                    break;
                case "admin":
                    log.info("******************addAdmin********************");
                    Admins admins = (Admins)o;
                    String strAdmin = String.format("%04d", new Random().nextInt(1001));
                    admins.setAdminId( "ADMIN_" + strAdmin + "_" + System.currentTimeMillis());
                    admins.setUserId(userId);
                    adminsDao.addAdmin(admins);
                    break;
            }
        }else {
            log.info("******************该用户已存在********************");
        }
    }

    @Override
    public List findAll(String type) {
        log.info("******************findAll********************");
        switch (type) {
            case "doctor":
                return doctorsDao.findAllDoctor();
            case "nurse":
                return nursesDao.findAllNurse();
            case "admin":
                return adminsDao.findAllAdmin();
            default:
                return null;
        }
    }

    @Override
    public void delete(String id, String type) {
        log.info("******************delete********************");
        String userId;
        switch (type) {
            case "doctor":
                userId = doctorsDao.findUserIdById(id);
                usersDao.deleteUserById(userId);
                doctorsDao.deleteDoctorById(id);
                break;
            case "nurse":
                userId = nursesDao.findUserIdById(id);
                usersDao.deleteUserById(userId);
                nursesDao.deleteNurseById(id);
                break;
            case "admin":
                userId = adminsDao.findUserIdById(id);
                usersDao.deleteUserById(userId);
                adminsDao.deleteAdminById(id);
                break;
        }
    }
}
