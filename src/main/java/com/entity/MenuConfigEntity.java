package com.entity;

public class MenuConfigEntity {

    private int menuId;//菜单id
    private String menuName;//菜单名字
    private int menuPid;//菜单父id
    private String menuUrl;//菜单url
    private String establishTime;//创建时间
    private int userId;//创建人员


    private String Extend1;
    private String Extend2;


    public MenuConfigEntity() {
    }

    public MenuConfigEntity(int menuId, String menuName, int menuPid, String menuUrl, String establishTime, int userId, String extend1, String extend2) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPid = menuPid;
        this.menuUrl = menuUrl;
        this.establishTime = establishTime;
        this.userId = userId;
        Extend1 = extend1;
        Extend2 = extend2;
    }

    @Override
    public String toString() {
        return "MenuConfigEntity{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuPid=" + menuPid +
                ", menuUrl='" + menuUrl + '\'' +
                ", establishTime='" + establishTime + '\'' +
                ", userId=" + userId +
                ", Extend1='" + Extend1 + '\'' +
                ", Extend2='" + Extend2 + '\'' +
                '}';
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(int menuPid) {
        this.menuPid = menuPid;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
