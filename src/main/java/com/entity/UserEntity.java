package com.entity;

import java.util.List;

public class UserEntity {


    private int userId; //用户ID
    private String loginName;//用户帐号
    private String password;//用户密码
    private String userName;//用户姓名
    private String phoneNumber;//手机号
    private String email;//电子邮箱
    private String createTime;//创建时间
    private String loginTime;//登录时间
    private String Extend1;//拓展字段1
    private String Extend2;//拓展字段2

    private List<RoleEntity> roleEntityList;
    private List<AuthorEntity> authorEntityList;


    public UserEntity() {
    }

    public UserEntity(int userId, String loginName, String password, String userName, String phoneNumber, String email, String createTime, String loginTime, String extend1, String extend2, List<RoleEntity> roleEntityList, List<AuthorEntity> authorEntityList) {
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createTime = createTime;
        this.loginTime = loginTime;
        Extend1 = extend1;
        Extend2 = extend2;
        this.roleEntityList = roleEntityList;
        this.authorEntityList = authorEntityList;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", Extend1='" + Extend1 + '\'' +
                ", Extend2='" + Extend2 + '\'' +
                ", roleEntityList=" + roleEntityList +
                ", authorEntityList=" + authorEntityList +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getExtend1() {
        return Extend1;
    }

    public void setExtend1(String extend1) {
        Extend1 = extend1;
    }

    public String getExtend2() {
        return Extend2;
    }

    public void setExtend2(String extend2) {
        Extend2 = extend2;
    }

    public List<RoleEntity> getRoleEntityList() {
        return roleEntityList;
    }

    public void setRoleEntityList(List<RoleEntity> roleEntityList) {
        this.roleEntityList = roleEntityList;
    }

    public List<AuthorEntity> getAuthorEntityList() {
        return authorEntityList;
    }

    public void setAuthorEntityList(List<AuthorEntity> authorEntityList) {
        this.authorEntityList = authorEntityList;
    }
}
