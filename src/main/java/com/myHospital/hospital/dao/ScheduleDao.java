package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Schedule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/31
 */
@Mapper
public interface ScheduleDao {

    //添加排班信息
    @Insert("INSERT INTO schedule(scheduleId,workTime,workDate,doctorId) VALUES(#{scheduleId},#{workTime},#{workDate},#{doctorId})")
    void addSchedule(Schedule schedule);

    //查询某个医生的所有排班
    @Select("SELECT * FROM schedule WHERE doctorId = #{doctorId}")
    List<Schedule> findScheduleByDoctorId(String doctorId);

    //查询未来一周的某个科室的值班医生

}
