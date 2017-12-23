package com.dao;


import com.entity.UserEntity;
import com.vo.UserAuthorVo;
import com.vo.UserRoleVo;

import java.util.List;

public interface UserDao {

    //登录
    public UserEntity userLogin(UserEntity userEntity);

    //用户对权限左侧菜单
    public List<UserAuthorVo> UserQueryAuthorAccordion(int userId);

    //用户对角色查询做菜单
    public List<UserRoleVo> UserQueryRoleAccordion(int userId);

    //查询所有用户
    public List<UserEntity> queryUserAll();

}
