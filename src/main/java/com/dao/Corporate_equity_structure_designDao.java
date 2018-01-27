package com.dao;

import com.entity.Corporate_equity_structure_designEntity;

import java.util.List;
import java.util.Map;

public interface Corporate_equity_structure_designDao {
   //查询所有
    public List<Corporate_equity_structure_designEntity> selectCorporate_equity_structure_designAll(Map<String, Object> map);
    //增加
    public void addOneCorporate_equity_structure_design(Corporate_equity_structure_designEntity corporate_equity_structure_designEntity);
    //删除
    public void delCorporate_equity_structure_design(Integer corporate_equity_structure_design_Id);
    //修改
    public void updateCorporate_equity_structure_design(Corporate_equity_structure_designEntity corporate_equity_structure_designEntity);
    //查询总数
    public int selectCorporate_equity_structure_designAllSum();
}
