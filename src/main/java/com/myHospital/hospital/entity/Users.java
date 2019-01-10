package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.Random;

public class Users {
    /**
     * userNo	用户编号	varchar	10	必填	唯一代表一名用户，格式固定
     * userName	用户姓名	varchar		必填	昵称
     * userPwd	用户密码	varchar		必填	6-12位，数字+字母组成
     * userSex	用户性别	int			男：0/女：1
     * userAge	用户年龄	int			只允许输入整数
     * userPhone	用户联系方式	varchar		必填
     * userAddress	用户住址	varchar
     * userCreateTime	用户添加时间	Timestamp		必填	根据系统时间自动生成
     * userUpdateTime	用户修改时间	Timestamp		必填	根据系统时间写入
     * */

    private String userNo;
    private String userName;
    private String userPwd;
    private Integer userSex;
    private Integer userAge;
    private String userPhone;
    private String userAddress;
    private Timestamp userCreateTime;
    private Timestamp userUpdateTime;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userPhone) {
        Random random = new Random();
        String str = String.format("%04d", random.nextInt(1001));
        this.userNo = "USER_" + str + "_" + System.currentTimeMillis();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Timestamp getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Timestamp userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Timestamp getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Timestamp userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }
}
