package com.myHospital.hospital.mapper;

import com.myHospital.hospital.entity.Doctors;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DoctorsDao {

    //添加医生
    @Insert("INSERT INTO doctors VALUES(#{doctorNo},#{doctorName},#{doctorPwd},#{doctorAge},#{doctorSex},#{doctorIDNum},#{doctorPhone},#{departmentNo},#{doctorTitle},#{doctorProfession},#{doctorMedicalServiceLife},#{doctorIntroduction})")
    int addDoctor(Doctors doctors);

    //通过doctorNo更新医生信息
    @Update("UPDATE doctors SET doctorName=#{doctorName},doctorSex=#{doctorSex},doctorAge=#{doctorAge},doctorPhone=#{doctorPhone},departmentNo=#{departmentNo},doctorTitle=#{doctorTitle},doctorProfession=#{doctorProfession},doctorMedicalServiceLife=#{doctorMedicalServiceLife},doctorIntroduction=#{doctorIntroduction} WHERE userNo=#{userNo}")
    int updateUserByNo(Doctors doctors);

    //通过doctorName查询医生信息
    @Select("SELECT * FROM doctors WHERE doctorName = #{doctorName}")
    Doctors findDoctorByName(String doctorName);

    //查询所有医生信息
    @Select("SELECT * FROM doctors")
    List<Doctors> findAllDoctor();

    //通过doctorNo删除医生信息
    @Delete("DELETE FROM doctors WHERE doctorNo=#{doctorNo}")
    int deleteDoctorByNo(String doctorNo);
}
