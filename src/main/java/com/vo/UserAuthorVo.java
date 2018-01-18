package com.vo;

public class UserAuthorVo {
    private int id;//权限id
    private String name;//权限名字
    private int pid;//权限父id
    private String authorUrl;
    private boolean isParent;
    private String authorMenu;//增删改操作

    public UserAuthorVo() {
    }

    public UserAuthorVo(int id, String name, int pid, String authorUrl, boolean isParent, String authorMenu) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.authorUrl = authorUrl;
        this.isParent = isParent;
        this.authorMenu = authorMenu;
    }

    @Override
    public String toString() {
        return "UserAuthorVo{" +
                "id=" + id +
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
