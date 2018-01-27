package com.service.impl;

import com.dao.ProjectDao;
import com.entity.ProjectEntity;
import com.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectDao fd;

    public List<ProjectEntity> selectProjectAll(Map<String,Object> map){
        return fd.selectProjectAll(map);
    }
    public void addOneProject(ProjectEntity projectEntity){
    };
    public void delProject(Integer project_Id){
    };
    public void updateProject(ProjectEntity projectEntity){

    };
    public int selectProjectAllSum(){
        return fd.selectProjectAllSum();
    };

}
