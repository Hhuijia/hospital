package com.myHospital.hospital.dao;

import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.RolePermission;
import com.myHospital.hospital.entity.UserRole;
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

    //添加角色—用户关系
    @Insert("INSERT INTO user_role VALUES(#{urId},#{userId},#{roleId})")
    void addUserRole(UserRole userRole);

    //添加角色—权限关系
    @Insert("INSERT INTO role_permission VALUES(#{rpId},#{roleId},#{permissionId})")
    void addRolePermission(RolePermission rolePermission);

    //添加权限
    @Insert("INSERT INTO permission(permissionId,permissionName) VALUES(#{permissionId},#{permissionName})")
    void addPermission(Permission permission);

//    //根据permissionId查询Name
//    @Select("SELECT permissionName FROM permission WHERE permissionId = #{permissionId}")
//    String findPerNameByPerId(String permissionName);

//    //查询所有权限
//    @Select("SELECT * FROM role_permission WHERE roleId = #{roleId}")
//    List<RolePermission> findAllPermissionByRoleId(String roleId);

    //查询所有权限
    @Select("SELECT * FROM permission")
    List<Permission> findAllPermission();

    //查询所有角色
    @Select("SELECT * FROM role")
    List<Role> findAllRole();

    //通过roleName查询roleId
    @Select("SELECT roleId FROM role WHERE roleName = #{roleName}")
    String findIdByName(String roleName);

    //查询角色对应权限
    @Select("SELECT p.permissionName FROM permission p WHERE permissionId IN (SELECT permissionId FROM role_permission rp WHERE rp.roleId = #{roleId)})")
    List<String> findPermissionByRoleId(String roleId);

    //通过roleId删除角色
    @Delete("DELETE FROM role WHERE roleId = #{roleId}")
    void deleteRoleById(String roleId);

    //通过roleId删除角色
    @Delete("DELETE FROM user_role WHERE roleId = #{roleId}")
    void deleteURByRoleId(String roleId);

    //通过userId删除角色
    @Delete("DELETE FROM user_role WHERE userId = #{userId}")
    void deleteURByUserId(String userId);

    //通过roleId删除角色-权限
    @Delete("DELETE FROM role_permission WHERE roleId = #{roleId}")
    void deleteRPByRoleId(String roleId);

    //通过permissionId删除角色-权限
    @Delete("DELETE FROM role_permission WHERE permissionId = #{permissionId}")
    void deleteRPByPermissionId(String permissionId);

    //通过permissionId删除权限
    @Delete("DELETE FROM Permission WHERE permissionId = #{permissionId}")
    void deletePermissionById(String permissionId);
}
