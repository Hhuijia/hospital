package com.myHospital.hospital.mapper;

import com.myHospital.hospital.entity.Doctors;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DoctorsDao {

     //添加医生
    @Insert("INSERT INTO doctors VALUES(#{doctorNo},#{doctorName},#{doctorPwd},#{doctorAge},#{doctorSex},#{doctorIDNum},#{doctorPhone},#{departmentNo},#{doctorTitle},#{doctorProfession},#{doctorMedicalServiceLife},#{doctorIntroduction})")
    void addDoctor(Doctors doctors);

//    //通过userNo更新用户信息
//    @Update("UPDATE users SET userName=#{userName},userSex=#{userSex},userAge=#{userAge},userAddress=#{userAddress} WHERE userNo=#{userNo}")
//    int updateUserByNo(Users user);

    //通过查询用户信息
    @Select("SELECT * FROM doctors WHERE doctorName = #{doctorName}")
    Doctors findDoctorByName(String doctorName);

    //查询所有医生信息
    @Select("SELECT * FROM doctors")
    List<Doctors> findAllDoctor();
}
