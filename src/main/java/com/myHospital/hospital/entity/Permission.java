package com.myHospital.hospital.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/21/2019
 */
public class Permission {
    private Integer permissionId;
    private String permissionName;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
