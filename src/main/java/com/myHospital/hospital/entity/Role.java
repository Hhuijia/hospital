package com.myHospital.hospital.entity;

import java.util.*;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/21/2019
 */
public class Role {
    private Integer roleId;
    private String roleName;
    private List<Users> users = new ArrayList<>();
    private List<Permission> permissions = new ArrayList<>();

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
