package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Appointment;
import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersService {

    //通过userIDNum更新用户信息
    int updateUserByIdNum(Users user);

    //通过userIDNum查找用户信息
    Users findUserByIDNum(String userIDNum);

    //通过userIDNum查找用户信息
    Users findUserByID(String userId);

    //通过userIDNum查找用户角色
    List<Role> findRoleByIDNum(String userIDNum);

    //通过roleId查找角色权限
    List<Permission> findPermissionByRoleId(String roleId);

    //查询所有用户（不包括医生护士管理员）
    List<Users> checkAllUser();

    //添加预约
    void makeAppointment(Appointment appointment);

    //查询预约记录
    List<Appointment> findAllAppointmentOfOneByUserId(String userId);

    //通过appointmentId更新预约状态
    void updateStatusById(String appointmentId);
}
