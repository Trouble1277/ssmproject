package com.service.impl;

import com.dao.Activity_modelDao;
import com.entity.Activity_modelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="Activity_modelServiceImpl")
public class Activity_modelServiceImpl {
    @Autowired
    Activity_modelDao activity_modelDao;
    //查询所有
    public List<Activity_modelEntity> selectContactsAll(Map<String, Object> map){
        return activity_modelDao.selectActivity_modelAll(map);
    };
    //增加
    public void addOneActivity_model(Activity_modelEntity activity_modelEntity){

    };
    //删除
    public void delActivity_model(Integer activity_model_Id){

    };
    //修改
    public void updateActivity_model(Activity_modelEntity activity_modelEntity){

    };
    //查询总数
    public int selectActivity_modelAllSum(){
       return activity_modelDao.selectActivity_modelAllSum();
    };
}
