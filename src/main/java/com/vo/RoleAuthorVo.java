package com.vo;

import com.entity.RoleEntity;
import com.entity.UserEntity;

public class RoleAuthorVo extends RoleEntity{


   String name;//所有的权限
   public RoleAuthorVo(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
