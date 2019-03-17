package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.GetMedicine;
import com.myHospital.hospital.entity.Pay;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 3/13/2019
 */
@Repository
public interface RecordService {

    //查询预约记录
    List<Appointment> findAllAppointment();

    //查询缴费记录
    List<Pay>  findAllPay();

    //查询取药记录
    List<GetMedicine>  findAllGetMedicine();

    //查询数据表列名
    List<String> findColumnName(String tableName);

    //通过医生ID查看个人预约记录
    List<Appointment> findTodayAppointment(String userId);
}
