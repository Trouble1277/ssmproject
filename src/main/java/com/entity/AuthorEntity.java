package com.entity;

import java.util.List;

public class AuthorEntity {

    private int id;//权限id
    private String name;//权限名字
    private int pid;//权限父id
    private String authorDescribe;//权限描述
    private String authorUrl;
    private boolean isParent;

    //private String authorMenu;//权限按钮
    //private int menuId;//菜单id

    private String Extend1;
    private String Extend2;


    private List<UserEntity> userEntityList;
    private List<RoleEntity> roleEntityList;

    public AuthorEntity(int id, String name, int pid, String authorDescribe, String authorUrl, boolean isParent, String extend1, String extend2, List<UserEntity> userEntityList, List<RoleEntity> roleEntityList) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.authorDescribe = authorDescribe;
        this.authorUrl = authorUrl;
        this.isParent = isParent;
        Extend1 = extend1;
        Extend2 = extend2;
        this.userEntityList = userEntityList;
        this.roleEntityList = roleEntityList;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", authorDescribe='" + authorDescribe + '\'' +
                ", authorUrl='" + authorUrl + '\'' +
                ", isParent=" + isParent +
                ", Extend1='" + Extend1 + '\'' +
                ", Extend2='" + Extend2 + '\'' +
                ", userEntityList=" + userEntityList +
                ", roleEntityList=" + roleEntityList +
                '}';
    }

    public AuthorEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getAuthorDescribe() {
        return authorDescribe;
    }

    public void setAuthorDescribe(String authorDescribe) {
        this.authorDescribe = authorDescribe;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        if (isParent == 0) {
            this.isParent = true;
        }else if(isParent == 1){
            this.isParent = false;
        }
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

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    public List<RoleEntity> getRoleEntityList() {
        return roleEntityList;
    }

    public void setRoleEntityList(List<RoleEntity> roleEntityList) {
        this.roleEntityList = roleEntityList;
    }
}
