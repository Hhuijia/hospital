package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersDao {

    //添加用户
    @Insert("INSERT INTO users(userId,userName,userPwd,salt,userSex,userAge,userIDNum,userPhone,userAddress) VALUES(#{userId},#{userName},#{userPwd},#{salt},#{userSex},#{userAge},#{userIDNum},#{userPhone},#{userAddress})")
    void addUser(Users user);

    //通过userIDNum更新用户信息
    @Update("UPDATE users SET userName=#{userName},userSex=#{userSex},userAge=#{userAge},userPhone=#{userPhone},userAddress=#{userAddress} WHERE userIDNum=#{userIDNum}")
    int updateUserByIdNum(Users user);

    //通过userIDNum更新用户信息
    @Update("UPDATE users SET userPwd=#{userPwd},salt=#{salt} WHERE userIDNum=#{userIDNum}")
    int updatePwdByIdNum(@Param("userPwd") String userPwd,@Param("salt") String salt, @Param("userIDNum") String userIDNum);

    //通过userIDNum查询用户信息
    @Select("SELECT * FROM users WHERE userIDNum = #{userIDNum}")
    Users findUserByIDNum(String userIDNum);

    //通过userIDNum查询用户ID
    @Select("SELECT userId FROM users WHERE userIDNum = #{userIDNum}")
    String findUserIdByIDNum(String userIDNum);

//    //通过userIDNum查询用户角色
//    @Select("SELECT roleName FROM role WHERE roleId in (SELECT roleId FROM users u, user_role ur WHERE userIDNum = #{userIDNum} AND u.userId = ur.userId)")
//    List<String> findRoleNameByIDNum(String userIDNum);

    //通过userIDNum查询用户角色
    @Select("SELECT * FROM role WHERE roleId in (SELECT roleId FROM users u, user_role ur WHERE userIDNum = #{userIDNum} AND u.userId = ur.userId)")
    List<Role> findRoleByIDNum(String userIDNum);

    //通过userIDNum查询用户角色
    @Select("SELECT * FROM permission WHERE permissionId in (SELECT permissionId FROM role_permission WHERE roleId = #{roleId})")
    List<Permission> findPermissionByRoleId(String roleId);

    //查询所有用户信息
    @Select("SELECT * FROM users WHERE userId not in (SELECT userId FROM doctors) AND userId not in (SELECT userId FROM nurses) AND userId not in (SELECT userId FROM admins) ")
    List<Users> checkAllUser();

    //通过userId查询用户信息
    @Select("SELECT * FROM users WHERE userId = #{userId}")
    Users findUserById(String userId);

    //通过userId删除用户信息
    @Delete("DELETE FROM users WHERE userId = #{userId}")
    void deleteUserById(String userId);

    //添加预约
    @Insert("INSERT INTO appointment(appointmentId,appointmentTime,userId,doctorId,departmentId) VALUES(#{appointmentId},#{appointmentTime},#{userId},#{doctorId},#{departmentId})")
    void makeAppointment(Appointment appointment);

    //查询预约记录
    @Select("SELECT * FROM appointment WHERE userId = #{userId}")
    @Results({
            @Result(property = "doctors",column = "doctorId",one = @One(select = "com.myHospital.hospital.dao.DoctorsDao.findDoctorById")),
            @Result(property = "users", column = "userId",one = @One(select = "com.myHospital.hospital.dao.UsersDao.findUserById"))
    })
    List<Appointment> findAllAppointmentOfOneByUserId(String userId);

    //通过appointmentId更新预约状态
    @Update("UPDATE appointment SET appointmentStatus=#{appointmentStatus} WHERE appointmentId=#{appointmentId}")
    void updateStatusById(@Param("appointmentId") String appointmentId, @Param("appointmentStatus") int appointmentStatus);
}
