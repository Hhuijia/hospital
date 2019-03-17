package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.*;
import com.myHospital.hospital.entity.*;
import com.myHospital.hospital.service.CommonService;
import com.myHospital.hospital.util.ExcelUtil;
import com.myHospital.hospital.util.PasswordHelper;
import org.apache.ibatis.annotations.One;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public void add(Users users, Object o, List roleIds, String type) {
        log.info("******************add********************");
        if (usersDao.findUserByIDNum(users.getUserIDNum()) == null){
            String strUser = String.format("%04d", new Random().nextInt(1001));
            users.setUserId( "USER_" + strUser + "_" + System.currentTimeMillis());
            users.setUserPwd("huanghuijia");
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(users);
            usersDao.addUser(users);
            String userId = usersDao.findUserIdByIDNum(users.getUserIDNum());
            for (Object roleId : roleIds){
                UserRole userRole = new UserRole();
                String strUR = String.format("%04d", new Random().nextInt(1001));
                userRole.setUrId( "USERROLE_" + strUR + "_" + System.currentTimeMillis());
                userRole.setUserId(userId);
                userRole.setRoleId(roleId.toString());
                rolePermissionDao.addUserRole(userRole);
            }
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
                default :
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
            default :
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
            default :
                break;
        }
    }

    @Override
    public  void batchImport(String fileName, MultipartFile file, String type) throws IOException{
        log.info("******************batchImport********************");
        ExcelUtil excelUtil = new ExcelUtil();
        if (!excelUtil.validateExcel(fileName)){
            log.info("******************文件格式不正确********************");
        }else {
            Sheet sheet = excelUtil.initImport(fileName, file);
            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                Users users = new Users();
                String userName = row.getCell(0).getStringCellValue();
                String userSex = row.getCell(1).getStringCellValue();
                int userAge = (int) row.getCell(2).getNumericCellValue();
                String userIDNum = row.getCell(3).getStringCellValue();
                row.getCell(4).setCellType(CellType.STRING);
                String userPhone = row.getCell(4).getStringCellValue();
                String userAddress = row.getCell(5).getStringCellValue();
                users.setUserName(userName);
                users.setUserSex(userSex);
                users.setUserAge(userAge);
                users.setUserIDNum(userIDNum);
                users.setUserPhone(userPhone);
                users.setUserAddress(userAddress);

                List<String> roleIds = Arrays.asList(row.getCell(6).getStringCellValue().split(","));

                switch (type) {
                    case "doctor":
                        Doctors doctors = new Doctors();
                        String doctorName = row.getCell(0).getStringCellValue();
                        String departmentName = row.getCell(7).getStringCellValue();
                        String doctorTitle = row.getCell(8).getStringCellValue();
                        String doctorProfession = row.getCell(9).getStringCellValue();
                        int doctorMedicalServiceLife = (int) row.getCell(10).getNumericCellValue();
                        String doctorIntroduction = row.getCell(11).getStringCellValue();
                        doctors.setDoctorName(doctorName);
                        doctors.setDepartmentName(departmentName);
                        doctors.setDoctorTitle(doctorTitle);
                        doctors.setDoctorProfession(doctorProfession);
                        doctors.setDoctorMedicalServiceLife(doctorMedicalServiceLife);
                        doctors.setDoctorIntroduction(doctorIntroduction);

                        add(users, doctors, roleIds, "doctor");
                        break;
                    case "nurse":
                        Nurses nurses = new Nurses();
                        String nurseName = row.getCell(0).getStringCellValue();
                        String nursePlace = row.getCell(7).getStringCellValue();
                        String nurseTitle = row.getCell(8).getStringCellValue();
                        nurses.setNurseName(nurseName);
                        nurses.setNursePlace(nursePlace);
                        nurses.setNurseTitle(nurseTitle);

                        add(users, nurses, roleIds, "nurse");
                        break;
                    case "admin":
                        Admins admins = new Admins();
                        String adminName = row.getCell(0).getStringCellValue();
                        String adminTitle = row.getCell(7).getStringCellValue();
                        admins.setAdminName(adminName);
                        admins.setAdminTitle(adminTitle);

                        add(users, admins, roleIds, "admin");
                        break;
                    default :
                        break;
                }
            }
        }
    }
}
