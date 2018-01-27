package com.service.impl;

import com.dao.Project_documentDao;
import com.entity.Project_documentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="Project_documentServiceImpl")
public class Project_documentServiceImpl {
    @Autowired
    Project_documentDao project_documentDao;
    //查询所有
    public List<Project_documentEntity> selectContactsAll(Map<String, Object> map){
        return project_documentDao.selectProject_documentAll(map);
    };
    //增加
    public void addOneProject_document(Project_documentEntity project_documentEntity){

    };
    //删除
    public void delProject_document(Integer project_document_Id){

    };
    //修改
    public void updateProject_document(Project_documentEntity project_documentEntity){

    };
    //查询总数
    public int selectProject_documentAllSum(){
       return project_documentDao.selectProject_documentAllSum();
    };
}
