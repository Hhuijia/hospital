package com.myHospital.hospital.service;

import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/3/10
 */
@Repository
public interface RolePermissionService {

    //添加角色
    void addRole(Role role, List<String> permissionName);

    //添加权限
    void addPermission(Permission permission);

    //查询所有权限
    List<Permission> findAllPermission();

    //查询所有角色
    List<Role> findAllRole();

    //删除角色
    void delRole(String roleId);

    //删除权限
    void delPermission(String permissionId);
}
