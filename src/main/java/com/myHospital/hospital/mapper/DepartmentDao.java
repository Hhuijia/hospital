package com.myHospital.hospital.mapper;

import com.myHospital.hospital.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentDao {

    //添加科室
    @Insert("INSERT INTO department VALUES(#{departmentNo},#{departmentName},#{departmentSystem},#{departmentInfo},#{departmentSymptom})")
    int addDepartment(Department department);

    //通过departmentNo删除科室
    @Delete("DELETE FROM doctors WHERE departmentNo=#{departmentNo}")
    int deleteDepartmentByNo(String departmentNo);

    //通过departmentName查询科室信息
    @Select("SELECT * FROM department WHERE departmentName = #{departmentName}")
    Department findDepartmentByName(String departmentName);

    //查询所有科室信息
    @Select("SELECT * FROM Department")
    List<Department> findAllDepartment();
}
