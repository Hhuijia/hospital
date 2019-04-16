package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Schedule;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/31
 */
@Repository
public interface ScheduleService {

    //查询某个医生的某天排班
    List<Schedule> findScheduleByDoctorId(String doctorId, String currentDay);

    //查询某一天的某个科室的所有值班医生
    List<Schedule> findScheduleCurrentDay(String currentDay, String departmentName);

    //查找某个医生7天内有排班的日期
    List<Schedule> findScheduleBetweenOneWeek(String doctorId);

    //上传排班文件
    void batchImportSchedule(String fileName, MultipartFile file) throws Exception;

    //通过ID查询排班
    Schedule findScheduleById(String scheduleId);
}
