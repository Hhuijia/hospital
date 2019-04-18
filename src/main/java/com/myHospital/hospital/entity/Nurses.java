package com.myHospital.hospital.entity;

import org.thymeleaf.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author QUEENEY
 * @date 2019/2/27
 */
public class Nurses {
    private String nurseId;
    private String nurseName;
    private String nurseTitle;
    private String nursePlace;
    private Timestamp nurseCreateTime;
    private Timestamp nurseUpdateTime;
    private String userId;

    private Users users;

    private List<String> roleName;

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getNurseTitle() {
        return nurseTitle;
    }

    public void setNurseTitle(String nurseTitle) {
        this.nurseTitle = nurseTitle;
    }

    public String getNursePlace() {
        return nursePlace;
    }

    public void setNursePlace(String nursePlace) {
        this.nursePlace = nursePlace;
    }

    public Timestamp getNurseCreateTime() {
        return nurseCreateTime;
    }

    public void setNurseCreateTime(Timestamp nurseCreateTime) {
        this.nurseCreateTime = nurseCreateTime;
    }

    public Timestamp getNurseUpdateTime() {
        return nurseUpdateTime;
    }

    public void setNurseUpdateTime(Timestamp nurseUpdateTime) {
        this.nurseUpdateTime = nurseUpdateTime;
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
