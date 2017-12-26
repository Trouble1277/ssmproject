package com.service;

import com.entity.UserEntity;
import com.vo.UserAuthorVo;
import com.vo.UserRoleVo;

import java.util.List;

public interface UserService {
    //登录
    public UserEntity userLogin(UserEntity userEntity);

    //用户对权限左侧菜单
    public List<UserAuthorVo> UserQueryAuthorAccordion(int userId);

    //用户对角色查询做菜单
    public List<UserRoleVo> UserQueryRoleAccordion(int userId);

    //带条件查询所有用户
    public List<UserEntity> queryUserAll(UserEntity userEntity);

    //修改用户信息
    public int updateUser(UserEntity userEntity);

    //增加用户
    public int addUser(UserEntity userEntity);

    //删除用户
    public int delUser(int userId);
}
