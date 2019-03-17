package com.myHospital.hospital.serviceImpl;

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

import java.util.List;

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
}
