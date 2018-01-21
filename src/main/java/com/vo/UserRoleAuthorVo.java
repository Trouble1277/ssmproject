package com.vo;

import com.entity.AuthorEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;

import java.util.List;

public class UserRoleAuthorVo extends UserEntity{


   String name;//所有的权限11
   String roleName;
   String userAuthorName;//用户对应的权限


   public UserRoleAuthorVo(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserAuthorName() {
        return userAuthorName;
    }

    public void setUserAuthorName(String userAuthorName) {
        this.userAuthorName = userAuthorName;
    }
}
