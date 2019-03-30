package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Doctors;
import com.myHospital.hospital.entity.Schedule;
import org.apache.ibatis.annotations.*;

import javax.print.Doc;
import java.util.List;

@Mapper
public interface DoctorsDao {

    //添加医生
    @Insert("INSERT INTO doctors(doctorId,doctorName,doctorTitle,doctorProfession,doctorMedicalServiceLife,doctorIntroduction,userId,departmentName) VALUES(#{doctorId},#{doctorName},#{doctorTitle},#{doctorProfession},#{doctorMedicalServiceLife},#{doctorIntroduction},#{userId},#{departmentName})")
    void addDoctor(Doctors doctors);

    //添加排班信息
    @Insert("INSERT INTO schedule(scheduleId,workTime,workDate,doctorId) VALUES(#{scheduleId},#{workTime},#{workDate},#{doctorId})")
    void addSchedule(Schedule schedule);

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

    //查询所有医生信息
    @Select("SELECT * FROM doctors")
    @Results({
            @Result(property = "users",column = "userId",one = @One(select = "com.myHospital.hospital.dao.UsersDao.findUserById"))
    })
    List<Doctors> findAllDoctor();

    //查询所有医生信息
    @Select("SELECT * FROM doctors")
    @Results({
            @Result(property = "schedules",column = "doctorId",many = @Many(select = "com.myHospital.hospital.dao.DoctorsDao.findScheduleByDoctorId"))
    })
    List<Doctors> findAllDoctorWithSchedule();

    //查询某个科室的所有医生
    @Select("SELECT * FROM doctors where departmentName = #{departmentName}")
    List<Doctors> findDoctorInSameDepartment(String departmentName);

    //查询某个医生的所有排班
    @Select("SELECT * FROM schedule where doctorId = #{doctorId}")
    List<Schedule> findScheduleByDoctorId(String doctorId);

    //通过doctorId删除医生信息
    @Delete("DELETE FROM doctors WHERE doctorId = #{doctorId}")
    void deleteDoctorById(String doctorId);
}
