package com.myHospital.hospital.entity;

/**
 * @author QUEENEY
 * @date 2019/3/11
 */
public class RolePermission {
    private String rpId;
    private String roleId;
    private String permissionId;

    public String getRpId() {
        return rpId;
    }

    public void setRpId(String rpId) {
        this.rpId = rpId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
