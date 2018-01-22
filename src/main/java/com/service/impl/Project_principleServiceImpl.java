package com.service.impl;

import com.dao.Project_principleDao;
import com.entity.Project_principleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="Project_principleServiceImpl")
public class Project_principleServiceImpl {
    @Autowired
    Project_principleDao project_principleDao;
    //查询所有
    public List<Project_principleEntity> selectProject_principleAll(Map<String, Object> map){
        return project_principleDao.selectProject_principleAll(map);
    };
    //增加
    public void addOneProject_principle(Project_principleEntity project_principleEntity){

    };
    //删除
    public void delProject_principle(Integer project_principle_Id){

    };
    //修改
    public void updateProject_principle(Project_principleEntity project_principleEntity){

    };
    //查询总数
    public int selectProject_principleAllSum(){
       return project_principleDao.selectProject_principleAllSum();
    };
}
