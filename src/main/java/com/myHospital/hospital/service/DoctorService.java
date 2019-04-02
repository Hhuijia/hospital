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
    //通过userId查询对应在医生表的信息
    Doctors findDoctorByUserId(String userId);

    //查询某个科室的所有医生
    List<Doctors> findDoctorInSameDepartment(String departmentName);
//
//    //查询某个科室的所有医生
//    List<String> findDoctorIdInSameDepartment(String departmentName);

    //查询某个科室的当天值班的医生
    List<Doctors> findDoctorToday(String departmentName, String currentDate);

}
