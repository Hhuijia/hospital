package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Doctors;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorsDao {

    //添加医生
    @Insert("INSERT INTO doctors(doctorId,doctorName,doctorTitle,doctorProfession,doctorMedicalServiceLife,doctorIntroduction,userId,departmentName) VALUES(#{doctorId},#{doctorName},#{doctorTitle},#{doctorProfession},#{doctorMedicalServiceLife},#{doctorIntroduction},#{userId},#{departmentName})")
    void addDoctor(Doctors doctors);

//    //通过doctorNo更新医生信息
//    @Update("UPDATE doctors SET doctorName=#{doctorName},doctorSex=#{doctorSex},doctorAge=#{doctorAge},doctorPhone=#{doctorPhone},departmentNo=#{departmentNo},doctorTitle=#{doctorTitle},doctorProfession=#{doctorProfession},doctorMedicalServiceLife=#{doctorMedicalServiceLife},doctorIntroduction=#{doctorIntroduction} WHERE userNo=#{userNo}")
//    int updateUserByNo(Doctors doctors);

    //通过doctorId查询对应在用户表的信息
    @Select("SELECT userId FROM doctors WHERE doctorId = #{doctorId}")
    String findUserIdById(String doctorId);

    //通过doctorId查询对应在用户表的信息
    @Select("SELECT * FROM doctors WHERE doctorId = #{doctorId}")
    String findDoctorById(String doctorId);

    //通过userId查询对应在医生表的信息
    @Select("SELECT * FROM doctors WHERE userId = #{userId}")
    Doctors findDoctorByUserId(String userId);

    //通过科室和医生姓名寻找医生Id
    @Select("SELECT doctorId FROM doctors WHERE departmentName = #{departmentName} AND doctorName = #{doctorName}")
    String findDoctorByName(@Param("departmentName") String departmentName, @Param("doctorName") String doctorName);

    //查询所有医生信息
    @Select("SELECT * FROM doctors")
    @Results({
            @Result(property = "users",column = "userId",one = @One(select = "com.myHospital.hospital.dao.UsersDao.findUserById"))
    })
    List<Doctors> findAllDoctor();

    //查询某个科室的所有医生
    @Select("SELECT * FROM doctors where departmentName = #{departmentName}")
    List<Doctors> findDoctorInSameDepartment(String departmentName);

    //查询某个科室的所有医生
    @Select("SELECT doctorId FROM doctors where departmentName = #{departmentName}")
    List<String> findDoctorIdInSameDepartment(String departmentName);

    //查询某个科室的当天值班的医生
    @Select("SELECT * FROM doctors WHERE departmentName=#{departmentName} AND doctorId IN (SELECT doctorId FROM schedule WHERE DATE_FORMAT(workDate,'%y%m%d')=#{currentDate})")
    List<Doctors> findDoctorToday(@Param("departmentName") String departmentName,  @Param("currentDate") String currentDate);

    //通过doctorId删除医生信息
    @Delete("DELETE FROM doctors WHERE doctorId = #{doctorId}")
    void deleteDoctorById(String doctorId);
}
