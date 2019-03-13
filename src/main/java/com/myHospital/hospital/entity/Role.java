package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.*;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/21/2019
 */
public class Role {
    private String roleId;
    private String roleName;
    private Timestamp roleCreateTime;
    private String perNameContain;
    private String rpId;

    private List<RolePermission> rolePermissions;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Timestamp getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(Timestamp roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public String getPerNameContain() {
        return perNameContain;
    }

    public void setPerNameContain(String perNameContain) {
        this.perNameContain = perNameContain;
    }

    public String getRpId() {
        return rpId;
    }

    public void setRpId(String rpId) {
        this.rpId = rpId;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
