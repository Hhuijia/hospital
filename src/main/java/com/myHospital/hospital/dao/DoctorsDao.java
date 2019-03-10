package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Users;
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

    //查询所有医生信息
    @Select("SELECT * FROM doctors")
    @Results({
            @Result(property = "users",column = "userId",one = @One(select = "com.myHospital.hospital.dao.UsersDao.findUserById"))
    })
    List<Doctors> findAllDoctor();

    //通过doctorId删除医生信息
    @Delete("DELETE FROM doctors WHERE doctorId = #{doctorId}")
    void deleteDoctorById(String doctorId);
}
