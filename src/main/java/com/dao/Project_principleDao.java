package com.dao;

import com.entity.Project_principleEntity;

import java.util.List;
import java.util.Map;

public interface Project_principleDao {
    //查询所有
    public List<Project_principleEntity> selectProject_principleAll(Map<String, Object> map);
    //增加
    public void addOneProject_principle(Project_principleEntity project_principleEntity);
    //删除
    public void delProject_principle(Integer project_principle_Id);
    //修改
    public void updateProject_principle(Project_principleEntity project_principleEntity);
    //查询总数
    public int selectProject_principleAllSum();


}
