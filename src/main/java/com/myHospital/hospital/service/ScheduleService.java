package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/31
 */
@Repository
public interface ScheduleService {

    //查询某个医生的所有排班
    List<Schedule> findScheduleByDoctorId(String doctorId);
}
