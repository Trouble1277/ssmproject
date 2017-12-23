package com.entity;

import java.util.List;

public class RoleEntity {

    private int roleId;//角色id
    private String roleName;//角色名字
    private int rolePid;//角色父id
    private String createTime;//角色创建时间
    private String roleDescribe;//角色描述
    private String Extend1;
    private String Extend2;

    private List<UserEntity> userEntityList;
    private List<AuthorEntity> authorEntityList;


    public RoleEntity() {
    }

    public RoleEntity(int roleId, String roleName, int rolePid, String createTime, String roleDescribe, String extend1, String extend2, List<UserEntity> userEntityList, List<AuthorEntity> authorEntityList) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.rolePid = rolePid;
        this.createTime = createTime;
        this.roleDescribe = roleDescribe;
        Extend1 = extend1;
        Extend2 = extend2;
        this.userEntityList = userEntityList;
        this.authorEntityList = authorEntityList;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", rolePid=" + rolePid +
                ", createTime='" + createTime + '\'' +
                ", roleDescribe='" + roleDescribe + '\'' +
                ", Extend1='" + Extend1 + '\'' +
                ", Extend2='" + Extend2 + '\'' +
                ", userEntityList=" + userEntityList +
                ", authorEntityList=" + authorEntityList +
                '}';
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRolePid() {
        return rolePid;
    }

    public void setRolePid(int rolePid) {
        this.rolePid = rolePid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
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

    public List<AuthorEntity> getAuthorEntityList() {
        return authorEntityList;
    }

    public void setAuthorEntityList(List<AuthorEntity> authorEntityList) {
        this.authorEntityList = authorEntityList;
    }
}
