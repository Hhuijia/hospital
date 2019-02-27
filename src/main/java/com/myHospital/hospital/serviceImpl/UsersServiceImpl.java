package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.controller.CommonController;
import com.myHospital.hospital.dao.UsersDao;
import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.UsersService;
import com.myHospital.hospital.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/17/2019
 */
@Repository
public class UsersServiceImpl implements UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersDao usersDao;

    @Override
    public void addUsers(Users users) {
        try {
            log.info("******************addUsers********************");
            String str = String.format("%04d", new Random().nextInt(1001));
            users.setUserId( "USER_" + str + "_" + System.currentTimeMillis());
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(users);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            users.setUserBirth(simpleDateFormat.format(users.getUserBirth()));
            log.info("******************当前user-[{}]********************",users);
            usersDao.addUser(users);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Users findUserByIDNum(String userIDNum) {
        Users user = new Users();
        try {
            user = usersDao.findUserByIDNum(userIDNum);

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Role> findRoleByIDNum(String userIDNum) {
        List<Role> roles =new ArrayList<>();
        try {
            roles.addAll(usersDao.findRoleByIDNum(userIDNum));
        }catch (Exception e){
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public List<Permission> findPermissionByRoleId(String roleId) {
        List<Permission> permissions =new ArrayList<>();
        try {
            permissions.addAll(usersDao.findPermissionByRoleId(roleId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return permissions;
    }
}
