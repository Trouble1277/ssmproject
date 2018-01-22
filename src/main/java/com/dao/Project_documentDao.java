package com.dao;

import com.entity.Project_documentEntity;

import java.util.List;
import java.util.Map;

public interface Project_documentDao {
   //查询所有
    public List<Project_documentEntity> selectProject_documentAll(Map<String, Object> map);
    //增加
    public void addOneProject_document(Project_documentEntity project_documentEntity);
    //删除
    public void delProject_document(Integer project_document_Id);
    //修改
    public void updateProject_document(Project_documentEntity project_documentEntity);
    //查询总数
    public int selectProject_documentAllSum();
}
