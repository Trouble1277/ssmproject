package com.service.impl;

import com.dao.UserDao;
import com.entity.*;
import com.service.UserService;
import com.vo.UserAuthorVo;
import com.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    public UserEntity userLogin(UserEntity userEntity) {
        return userDao.userLogin(userEntity);
    }

    public int addUserRole(UserRoleEntity userRoleEntity) {
        return userDao.addUserRole(userRoleEntity);
    }

    public int delUserRole(int userId) {
        return userDao.delUserRole(userId);
    }

    public int updateRole(RoleEntity roleEntity) {
        return userDao.updateRole(roleEntity);
    }

    public int addRole(RoleEntity roleEntity) {
        return userDao.addRole(roleEntity);
    }

    public int delRole(int roleId) {
        return userDao.delRole(roleId);
    }

    public List<UserAuthorVo> queryUserAllAuthor(int userId) {
        return userDao.queryUserAllAuthor(userId);
    }

    public List<UserAuthorVo> UserQueryAuthorAccordion(int userId) {
        return userDao.UserQueryAuthorAccordion(userId);
    }

    public List<UserRoleVo> UserQueryRoleAccordion(int userId) {
        return userDao.UserQueryRoleAccordion(userId);
    }

    public List<UserEntity> queryUserAll(Map<String,Object> map) {
        return userDao.queryUserAll(map);
    }

    public int queryUserAllCount(Map<String,Object> map) {
        return userDao.queryUserAllCount(map);
    }

    public List<UserRoleVo> queryUserRole(int userId) {
        return userDao.queryUserRole(userId);
    }

    public int updateUser(UserEntity userEntity) {
       return userDao.updateUser(userEntity);
    }

    public int addUser(UserEntity userEntity) {
        return userDao.addUser(userEntity);
    }

    public int delUser(int userId) {
        return userDao.delUser(userId);
    }

    public List<AuthorEntity> queryAuthorAll(int authorPid) {
        return userDao.queryAuthorAll(authorPid);
    }

    public List<RoleEntity> queryComboRoleAll() {
        return userDao.queryComboRoleAll();
    }

    public int addUserAuthor(UserAuthorEntity userAuthorEntity) {
        return userDao.addUserAuthor(userAuthorEntity);
    }

    public int delUserAuthor(int userId) {
        return userDao.delUserAuthor(userId);
    }


    public List<AuthorEntity> queryAuthorAllManage(Map<String, Object> map) {
        return userDao.queryAuthorAllManage(map);
    }

    public int queryAuthorAllManageCount(Map<String, Object> map) {
        return userDao.queryAuthorAllManageCount(map);
    }

    public int addAuthor(AuthorEntity authorEntity) {
        return userDao.addAuthor(authorEntity);
    }

    public int delAuthor(int id) {
        return userDao.delAuthor(id);
    }

    public AuthorEntity queryAuthorById(int id) {
        return userDao.queryAuthorById(id);
    }

    public List<RoleEntity> queryRoleAllManage(Map<String, Object> map) {
        return userDao.queryRoleAllManage(map);
    }

    public List<RoleEntity> queryRoleAllManageCount(Map<String, Object> map) {
        return userDao.queryRoleAllManageCount(map);
    }

    public int updateAuthor(AuthorEntity authorEntity) {
        return userDao.updateAuthor(authorEntity);
    }
}
