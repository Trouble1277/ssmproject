package com.service.impl;

import com.dao.Corporate_visit_recordDao;
import com.entity.Corporate_visit_recordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="Corporate_visit_recordServiceImpl")
public class Corporate_visit_recordServiceImpl {
    @Autowired
    Corporate_visit_recordDao corporate_visit_recordDao;
    //查询所有
    public List<Corporate_visit_recordEntity> selectCorporate_visit_recordAll(Map<String, Object> map){
        return corporate_visit_recordDao.selectCorporate_visit_recordAll(map);
    };
    //增加
    public void addOneCorporate_visit_record(Corporate_visit_recordEntity corporate_visit_recordEntity){

    };
    //删除
    public void delCorporate_visit_record(Integer corporate_visit_record_Id){

    };
    //修改
    public void updateCorporate_visit_record(Corporate_visit_recordEntity corporate_visit_recordEntity){

    };
    //查询总数
    public int selectCorporate_visit_recordAllSum(){
       return corporate_visit_recordDao.selectCorporate_visit_recordAllSum();
    };
}
