package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.GetMedicine;
import com.myHospital.hospital.entity.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    @Select("SELECT * FROM appointment WHERE doctorId = #{doctorId} AND DATE_FORMAT(appointmentTime,'%Y%m%d') = #{appointmentTime}")
    List<Appointment> findTodayAppointment(String doctorId, String appointmentTime);
}
