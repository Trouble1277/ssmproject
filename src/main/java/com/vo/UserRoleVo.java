package com.vo;

public class UserRoleVo {
    private int roleId;
    private String roleName;
    private int authorId;//权限id
    private String authorName;//权限名字
    private int authorPid;//权限父id
    private String authorUrl;
    private boolean isParent;
    private String authorMenu;//增删改操作

    public UserRoleVo() {
    }

    public UserRoleVo(int roleId, String roleName, int authorId, String authorName, int authorPid, String authorUrl, boolean isParent, String authorMenu) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorPid = authorPid;
        this.authorUrl = authorUrl;
        this.isParent = isParent;
        this.authorMenu = authorMenu;
    }

    @Override
    public String toString() {
        return "UserRoleVo{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorPid=" + authorPid +
                ", authorUrl='" + authorUrl + '\'' +
                ", isParent=" + isParent +
                ", authorMenu='" + authorMenu + '\'' +
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorPid() {
        return authorPid;
    }

    public void setAuthorPid(int authorPid) {
        this.authorPid = authorPid;
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
