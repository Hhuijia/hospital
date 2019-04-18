package com.myHospital.hospital.entity;

import org.thymeleaf.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

public class Admins {

    private String adminId;
    private String adminName;
    private String adminTitle;
    private Timestamp adminCreateTime;
    private Timestamp adminUpdateTime;
    private String userId;

    private Users users;

    private List<String> roleName;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminTitle() {
        return adminTitle;
    }

    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
    }

    public Timestamp getAdminCreateTime() {
        return adminCreateTime;
    }

    public void setAdminCreateTime(Timestamp adminCreateTime) {
        this.adminCreateTime = adminCreateTime;
    }

    public Timestamp getAdminUpdateTime() {
        return adminUpdateTime;
    }

    public void setAdminUpdateTime(Timestamp adminUpdateTime) {
        this.adminUpdateTime = adminUpdateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getRoleName() {
        return StringUtils.join(roleName,",");
    }

    public void setRoleName(List<String> roleName) {
        this.roleName = roleName;
    }
}
