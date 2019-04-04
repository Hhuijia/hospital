package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.GetMedicine;
import com.myHospital.hospital.entity.Pay;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 3/13/2019
 */
@Mapper
public interface RecordDao {

    //查询所有预约记录
    @Select("SELECT * FROM appointment")
    List<Appointment>  findAllAppointment();

    //查询所有缴费记录
    @Select("SELECT * FROM paym")
    List<Pay>  findAllPay();

    //查询所有取药记录
    @Select("SELECT * FROM getMedicine")
    List<GetMedicine>  findAllGetMedicine();

    //查询数据表列名
    @Select("SELECT column_name FROM information_schema.columns WHERE table_schema='hospital' AND table_name=#{tableName}")
    List<String> findColumnName(String tableName);

    //通过医生ID查看个人预约记录(年月日)
    @Select("SELECT * FROM appointment WHERE doctorId=#{doctorId} AND DATE_FORMAT(appointmentTime,'%Y%m%d')=#{appointmentTime} AND appointmentStatus='1' ORDER BY appointmentTime, appointmentCreatedTime")
    @Results({
            @Result(property = "doctors",column = "doctorId",one = @One(select = "com.myHospital.hospital.dao.DoctorsDao.findDoctorById")),
            @Result(property = "users", column = "userId",one = @One(select = "com.myHospital.hospital.dao.UsersDao.findUserById"))
    })
    List<Appointment> findTodayAppointment(@Param("doctorId") String doctorId,  @Param("appointmentTime") String appointmentTime);


    //通过医生ID查看个人近期预约记录(年月日)
    @Select("SELECT * FROM appointment WHERE doctorId=#{doctorId} AND appointmentTime BETWEEN #{before3Day} AND #{after7Day} ORDER BY appointmentTime, appointmentCreatedTime")
    @Results({
            @Result(property = "doctors",column = "doctorId",one = @One(select = "com.myHospital.hospital.dao.DoctorsDao.findDoctorById")),
            @Result(property = "users", column = "userId",one = @One(select = "com.myHospital.hospital.dao.UsersDao.findUserById"))
    })
    List<Appointment> findRecentAppointment(@Param("doctorId") String doctorId,  @Param("before3Day") String before3Day, @Param("after7Day") String after7Day);
}
