package com.service.impl;

import com.dao.UserDao;
import com.entity.UserEntity;
import com.service.UserService;
import com.vo.UserAuthorVo;
import com.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    public UserEntity userLogin(UserEntity userEntity) {
        return userDao.userLogin(userEntity);
    }

    public List<UserAuthorVo> UserQueryAuthorAccordion(int userId) {
        return userDao.UserQueryAuthorAccordion(userId);
    }

    public List<UserRoleVo> UserQueryRoleAccordion(int userId) {
        return userDao.UserQueryRoleAccordion(userId);
    }

    public List<UserEntity> queryUserAll(UserEntity userEntity) {
        return userDao.queryUserAll(userEntity);
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
}
