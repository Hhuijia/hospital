package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Admins;
import com.myHospital.hospital.entity.Nurses;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminsDao {

    //添加管理员
    @Insert("INSERT INTO admins(adminId,adminName,adminTitle,userId) VALUES(#{adminId},#{adminName},#{adminTitle},#{userId})")
    void addAdmin(Admins admins);

    //通过adminsId查询对应在用户表的信息
    @Select("SELECT userId FROM admins WHERE adminId = #{adminId}")
    String findUserIdById(String adminId);

    //查询所有管理员信息
    @Select("SELECT * FROM admins")
    @Results({
            @Result(property = "users",column = "userId",one = @One(select = "com.myHospital.hospital.dao.UsersDao.findUserById"))
    })
    List<Admins> findAllAdmin();

    //通过adminsId删除管理员
    @Delete("DELETE FROM admins WHERE adminId = #{adminId}")
    void deleteAdminById(String adminId);
}
