package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Users;

import java.util.List;

public abstract class UsersService {

    public abstract List<Users> findAllUser();
    public abstract int addUsers();

}
