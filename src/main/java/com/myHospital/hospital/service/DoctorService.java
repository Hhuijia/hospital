package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/9
 */
@Repository
public interface DoctorService {

    //添加医生
    void addDoctor(Users users, Doctors doctors, String userIDNum, String departmentName);

    //查询所有医生信息
    List<Doctors> findAllDoctor();

    //通过doctorId删除医生信息
    void deleteDoctor(String doctorId);

}
