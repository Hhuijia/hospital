package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/10
 */
@Mapper
public interface RolePermissionDao {

    //添加角色
    @Insert("INSERT INTO role(roleId,roleName) VALUES(#{roleId},#{roleName})")
    void addRole(Role role);

    //添加角色—权限关系
    @Insert("INSERT INTO role_permission VALUES(#{rpId},#{roleId},#{permissionId})")
    void addRolePermission(RolePermission rolePermission);

    //添加权限
    @Insert("INSERT INTO permission(permissionId,permissionName) VALUES(#{permissionId},#{permissionName})")
    void addPermission(Permission permission);

    //根据permissionId查询Name
    @Select("SELECT permissionName FROM permission WHERE permissionId = #{permissionId}")
    String findPerNameByPerId(String permissionName);

    //查询所有权限
    @Select("SELECT * FROM role_permission WHERE roleId = #{roleId}")
    List<RolePermission> findAllPermissionByRoleId(String roleId);

    //查询所有权限
    @Select("SELECT * FROM permission")
    List<Permission> findAllPermission();

    //查询所有角色
    @Select("SELECT * FROM role")
    @Results({
            @Result(property = "rolePermissions",column = "roleId",many = @Many(select = "com.myHospital.hospital.dao.RolePermissionDao.findAllPermissionByRoleId"))
    })
    List<Role> findAllRole();
}
