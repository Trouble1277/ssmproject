package com.entity;

public class UserRoleEntity {

    private int SerialNO;//标识列
    private UserEntity userEntity;
    private RoleEntity roleEntity;

    private String Extend1;
    private String Extend2;



    public UserRoleEntity() {
    }

    public UserRoleEntity(int serialNO, UserEntity userEntity, RoleEntity roleEntity, String extend1, String extend2) {
        SerialNO = serialNO;
        this.userEntity = userEntity;
        this.roleEntity = roleEntity;
        Extend1 = extend1;
        Extend2 = extend2;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "SerialNO=" + SerialNO +
                ", userEntity=" + userEntity +
                ", roleEntity=" + roleEntity +
                ", Extend1='" + Extend1 + '\'' +
                ", Extend2='" + Extend2 + '\'' +
                '}';
    }

    public int getSerialNO() {
        return SerialNO;
    }

    public void setSerialNO(int serialNO) {
        SerialNO = serialNO;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
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
}
