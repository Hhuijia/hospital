package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.Random;

public class Admins {

    /**
     * adminNo	管理员编号	varchar		必填	唯一代表一名管理员，格式固定
     * adminName	管理员姓名	varchar		必填	真实姓名
     * adminPwd	管理员密码	varchar		必填	6-12位，数字+字母组成
     * domain	管理员权限	varchar		必填
     * adminCreateTime	用户添加时间	date		必填	根据系统时间自动生成
     * adminUpdateTime	用户修改时间	date		必填	根据系统时间写入
     */

    private String adminNo;
    private String adminName;
    private String adminPwd;
    private String domain;
    private Timestamp adminCreateTime;
    private Timestamp adminUpdateTime;

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo() {
        Random random = new Random();
        String str = String.format("%04d", random.nextInt(1001));
        this.adminNo = "ADMIN_" + str + "_" + System.currentTimeMillis();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
}
