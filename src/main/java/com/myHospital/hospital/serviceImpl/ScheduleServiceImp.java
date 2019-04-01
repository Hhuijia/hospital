package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.ScheduleDao;
import com.myHospital.hospital.entity.Schedule;
import com.myHospital.hospital.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/31
 */
@Repository
public class ScheduleServiceImp implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public List<Schedule> findScheduleByDoctorId(String doctorId) {
        return scheduleDao.findScheduleByDoctorId(doctorId);
    }
}
