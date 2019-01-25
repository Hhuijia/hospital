package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.UsersDao;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/17/2019
 */
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public void addUsers(Users users) {
        try {
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
    public String findRoleByIDNum(String userIDNum) {
        String role = "";
        try {
            role = usersDao.findRoleByIDNum(userIDNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return role;
    }
}
