package com.dao;

import com.entity.Corporate_visit_recordEntity;

import java.util.List;
import java.util.Map;

public interface Corporate_visit_recordDao {
    //查询所有
    public List<Corporate_visit_recordEntity> selectCorporate_visit_recordAll(Map<String, Object> map);
    //增加
    public void addOneCorporate_visit_record(Corporate_visit_recordEntity corporate_visit_recordEntity);
    //删除
    public void delCorporate_visit_record(Integer corporate_visit_record_Id);
    //修改
    public void updateCorporate_visit_record(Corporate_visit_recordEntity corporate_visit_recordEntity);
    //查询总数
    public int selectCorporate_visit_recordAllSum();


}
