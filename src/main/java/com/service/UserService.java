package com.service;

import com.entity.*;
import com.vo.UserAuthorVo;
import com.vo.UserRoleVo;

import java.util.List;
import java.util.Map;

public interface UserService {
    //登录
    public UserEntity userLogin(UserEntity userEntity);

    //根据用户id查询他的所有权限
    public List<UserAuthorVo> queryUserAllAuthor(int userId);

    //用户对权限左侧菜单
    public List<UserAuthorVo> UserQueryAuthorAccordion(int userId);

    //用户对角色查询做菜单
    public List<UserRoleVo> UserQueryRoleAccordion(int userId);

    //查询所有用户
    public List<UserEntity> queryUserAll(Map<String,Object> map);

    //查询所有用户总数
    public int queryUserAllCount(Map<String,Object> map);

    //根据用户查询角色
    public List<UserRoleVo> queryUserRole(int userId);

    //修改用户信息
    public int updateUser(UserEntity userEntity);

    //增加用户
    public int addUser(UserEntity userEntity);

    //删除用户
    public int delUser(int userId);

    //查询所有权限
    public List<AuthorEntity> queryAuthorAll(int authorPid);

    //根据id查权限名称
    public AuthorEntity queryAuthorById(int id);

    //查询所有角色
    public List<RoleEntity> queryComboRoleAll();

    //增加用户权限
    public int addUserAuthor(UserAuthorEntity userAuthorEntity);

    //删除用户权限
    public int delUserAuthor(int userId);

    //查询所有角色
    public List<RoleEntity> queryRoleAllManage(Map<String,Object> map);

    //查询所有角色的总数
    public List<RoleEntity> queryRoleAllManageCount(Map<String,Object> map);

    //修改角色信息
    public int updateRole(RoleEntity roleEntity);

    //增加角色
    public int addRole(RoleEntity roleEntity);

    //删除角色
    public int delRole(int roleId);

    //增加用户角色
    public int addUserRole(UserRoleEntity userRoleEntity);

    //删除用户角色
    public int delUserRole(int userId);


    //查询权限管理模块
    public List<AuthorEntity> queryAuthorAllManage(Map<String,Object> map);

    //查询权限管理模块总数
    public int queryAuthorAllManageCount(Map<String,Object> map);


    //增加权限
    public int addAuthor(AuthorEntity authorEntity);

    //删除权限
    public int delAuthor(int id);

    //修改权限
    public int updateAuthor(AuthorEntity authorEntity);

}
