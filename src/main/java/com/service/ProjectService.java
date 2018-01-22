package com.service;

import com.entity.ProjectEntity;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    public List<ProjectEntity> selectProjectAll(Map<String, Object> map);
    public void addOneProject(ProjectEntity projectEntity);
    public void delProject(Integer project_Id);
    public void updateProject(ProjectEntity projectEntity);
    public int selectProjectAllSum();
}
