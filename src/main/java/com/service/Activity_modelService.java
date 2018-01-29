package com.service;

import com.entity.Activity_modelEntity;
import com.entity.BlacklistEntity;

import java.util.List;
import java.util.Map;

public interface Activity_modelService {
 //查询所有
 public List<Activity_modelEntity> selectActivity_modelAll(Map<String, Object> map);
 //增加
 public void addOneActivity_model(Activity_modelEntity activity_modelEntity);
 //删除
 public void delActivity_model(Integer activity_model_Id);
 //修改
 public void updateActivity_model(Activity_modelEntity activity_modelEntity);
 //查询总数
 public int selectActivity_modelAllSum();
}
