package com.vo;

public class UserRoleVo {
    private int roleId;
    private String roleName;
    private int id;//权限id
    private String name;//权限名字
    private int pid;//权限父id
    private String authorUrl;
    private boolean isParent;
    private String authorMenu;//增删改操作

    public UserRoleVo() {
    }

    public UserRoleVo(int roleId, String roleName, int id, String name, int pid, String authorUrl, boolean isParent, String authorMenu) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.authorUrl = authorUrl;
        this.isParent = isParent;
        this.authorMenu = authorMenu;
    }

    @Override
    public String toString() {
        return "UserRoleVo{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", authorUrl='" + authorUrl + '\'' +
                ", isParent=" + isParent +
                ", authorMenu='" + authorMenu + '\'' +
                '}';
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
    public String getAuthorMenu() {
        return authorMenu;
    }

    public void setAuthorMenu(String authorMenu) {
        this.authorMenu = authorMenu;
    }
}
