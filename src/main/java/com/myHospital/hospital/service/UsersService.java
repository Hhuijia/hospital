package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Users;

public interface UsersService {

    //添加用户
    void addUsers(Users users);

    //通过userIDNum查找用户信息
    Users findUserByIDNum(String userIDNum);

    //通过userIDNum查找用户角色
    String findRoleByIDNum(String userIDNum);

}
