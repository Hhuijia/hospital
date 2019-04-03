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

    //查询某个医生的某天排班
    @Select("SELECT * FROM schedule WHERE doctorId = #{doctorId} AND DATE_FORMAT(workDate,'%Y%m%d')=#{currentDay}")
    List<Schedule> findScheduleByDoctorId(@Param("doctorId") String doctorId, @Param("currentDay") String currentDay);

    //查询某一天的某个科室的所有值班医生
    @Select("SELECT * FROM schedule WHERE departmentName=#{departmentName} AND DATE_FORMAT(workDate,'%Y%m%d')=#{currentDay}")
    List<Schedule> findScheduleCurrentDay(@Param("currentDay") String currentDay, @Param("departmentName") String departmentName);

    //查找某个医生7天内有排班的日期
    @Select("SELECT s.workDate FROM (SELECT * FROM schedule WHERE doctorId=#{doctorId}) AS s GROUP BY s.workDate HAVING s.workDate BETWEEN #{currentDate} AND #{after7Day}")
    List<Schedule> findScheduleBetweenOneWeek(@Param("doctorId") String doctorId, @Param("currentDate") String currentDate, @Param("after7Day") String after7Day);
}
