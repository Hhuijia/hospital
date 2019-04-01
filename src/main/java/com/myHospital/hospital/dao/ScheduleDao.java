package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Schedule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/31
 */
@Mapper
public interface ScheduleDao {

    //添加排班信息
    @Insert("INSERT INTO schedule(scheduleId,workTime,workDate,remain,doctorId,doctorName,departmentName) VALUES(#{scheduleId},#{workTime},#{workDate},#{remain},#{doctorId},#{doctorName},#{departmentName})")
    void addSchedule(Schedule schedule);

    //查询某个医生的所有排班
    @Select("SELECT * FROM schedule WHERE doctorId = #{doctorId}")
    List<Schedule> findScheduleByDoctorId(String doctorId);

    //查询未来一周的某个科室的值班医生
    @Select("SELECT * FROM schedule WHERE doctorId = #{doctorId} AND DATE_FORMAT(currentDay,'%Y%m%d')=#{currentDay}")
    List<Schedule> findScheduleCurrentDay(@Param("currentDay") String currentDay, @Param("doctorId") String doctorId);
}
