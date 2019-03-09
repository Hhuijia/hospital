package com.myHospital.hospital.service;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/9
 */
@Repository
public interface DepartmentService {

    //查询所有科室名称
    List<String> findAlldepartmentName();

    //通过科室名称查询科室ID
    String findDepartmentIdByName(String departmentName);
}
