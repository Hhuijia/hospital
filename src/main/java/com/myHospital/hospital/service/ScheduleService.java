package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Schedule;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/31
 */
@Repository
public interface ScheduleService {

    //查询某个医生的所有排班
    List<Schedule> findScheduleByDoctorId(String doctorId);

    //上传排班文件
    void batchImportSchedule(String fileName, MultipartFile file) throws Exception;
}
