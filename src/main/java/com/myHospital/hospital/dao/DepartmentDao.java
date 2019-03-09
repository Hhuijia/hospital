package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentDao {

//    //添加科室
//    @Insert("INSERT INTO department VALUES(#{departmentId},#{departmentName},#{departmentSystem},#{departmentInfo},#{departmentSymptom},,)")
//    int addDepartment(Department department);
//
//    //通过departmentId删除科室
//    @Delete("DELETE FROM department WHERE departmentId=#{departmentId}")
//    int deleteDepartmentByNo(String departmentNo);

    //通过departmentName查询科室ID
    @Select("SELECT departmentId FROM department WHERE departmentName=#{departmentName}")
    String findDepartmentIdByName(String departmentName);

//    //通过departmentName查询科室信息
//    @Select("SELECT * FROM department WHERE departmentName = #{departmentName}")
//    Department findDepartmentByName(String departmentName);

    //查询所有科室信息
    @Select("SELECT * FROM department")
    List<Department> findAllDepartment();

    //查询所有科室名称
    @Select("SELECT departmentName FROM department")
    List<String> findAlldepartmentName();
}
