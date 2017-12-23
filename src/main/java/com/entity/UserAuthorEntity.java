package com.entity;

public class UserAuthorEntity {

    private int SerialNO;//标识列
    private UserEntity userEntity;
    private AuthorEntity authorEntity;
    private String authorMenu;//增删改操作
    private String Extend1;
    private String Extend2;



    public UserAuthorEntity() {
    }

    public UserAuthorEntity(int serialNO, UserEntity userEntity, AuthorEntity authorEntity, String authorMenu, String extend1, String extend2) {
        SerialNO = serialNO;
        this.userEntity = userEntity;
        this.authorEntity = authorEntity;
        this.authorMenu = authorMenu;
        Extend1 = extend1;
        Extend2 = extend2;
    }

    @Override
    public String toString() {
        return "UserAuthorEntity{" +
                "SerialNO=" + SerialNO +
                ", userEntity=" + userEntity +
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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
