package com.myHospital.hospital.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Users {
    /**
     * userId	用户ID	varchar	10	必填	唯一代表一名用户，格式固定
     * roleId	角色ID	varchar	11	必填	外键
     * userName	用户姓名	varchar		必填	真实姓名
     * userPwd	用户密码	varchar		必填	6-12位，数字+字母组成
     * userSex	用户性别	int		必填	男：0/女：1
     * userBirth	用户出生日期	date		必填
     * userAge	用户年龄	int		必填	只允许输入整数
     * userIDNum	用户身份证号	varchar		必填	身份证号格式
     * userPhone	用户联系方式	varchar		必填
     * userAddress	用户住址	varchar		必填
     * userCreateTime	用户添加时间	date		必填	根据系统时间自动生成
     * userUpdateTime	用户修改时间	timestamp		必填	根据系统时间写入
     * doctor_id	医生ID	varchar		若角色为医生则必填
     *
     * */

    private String userId;
    private String userName;
    private String userPwd;
    private Integer userSex;
    private Integer userAge;
    private Timestamp userBirth;
    private String userIDNum;
    private String userPhone;
    private String userAddress;
    private Timestamp userCreateTime;
    private Timestamp userUpdateTime;
    private String doctor_id;

    private List<Role> role = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId() {
        Random random = new Random();
        String str = String.format("%04d", random.nextInt(1001));
        this.userId = "USER_" + str + "_" + System.currentTimeMillis();
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

    public Timestamp getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Timestamp userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserIDNum() {
        return userIDNum;
    }

    public void setUserIDNum(String userIDNum) {
        this.userIDNum = userIDNum;
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

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
