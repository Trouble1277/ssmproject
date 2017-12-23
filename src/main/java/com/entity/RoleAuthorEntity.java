package com.entity;

public class RoleAuthorEntity {

    private int SerialNO;//标识列
    private RoleEntity roleEntity;
    private AuthorEntity authorEntity;
    private String authorMenu;//增删改操作
    private String Extend1;
    private String Extend2;



    public RoleAuthorEntity() {
    }

    public RoleAuthorEntity(int serialNO, RoleEntity roleEntity, AuthorEntity authorEntity, String authorMenu, String extend1, String extend2) {
        SerialNO = serialNO;
        this.roleEntity = roleEntity;
        this.authorEntity = authorEntity;
        this.authorMenu = authorMenu;
        Extend1 = extend1;
        Extend2 = extend2;
    }

    @Override
    public String toString() {
        return "RoleAuthorEntity{" +
                "SerialNO=" + SerialNO +
                ", roleEntity=" + roleEntity +
                ", authorEntity=" + authorEntity +
                ", authorMenu='" + authorMenu + '\'' +
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

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }

    public String getAuthorMenu() {
        return authorMenu;
    }

    public void setAuthorMenu(String authorMenu) {
        this.authorMenu = authorMenu;
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
