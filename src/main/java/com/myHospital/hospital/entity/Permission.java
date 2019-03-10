package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/21/2019
 */
public class Permission {
    private String permissionId;
    private String permissionName;
    private Timestamp permissionCreateTime;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Timestamp getPermissionCreateTime() {
        return permissionCreateTime;
    }

    public void setPermissionCreateTime(Timestamp permissionCreateTime) {
        this.permissionCreateTime = permissionCreateTime;
    }
}
