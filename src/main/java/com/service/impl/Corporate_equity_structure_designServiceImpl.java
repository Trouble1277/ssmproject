package com.service.impl;

import com.dao.Corporate_equity_structure_designDao;
import com.entity.Corporate_equity_structure_designEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="Corporate_equity_structure_designServiceImpl")
public class Corporate_equity_structure_designServiceImpl {
    @Autowired
    Corporate_equity_structure_designDao corporate_equity_structure_designDao;
    //查询所有
    public List<Corporate_equity_structure_designEntity> selectCorporate_equity_structure_designAll(Map<String, Object> map){
        return corporate_equity_structure_designDao.selectCorporate_equity_structure_designAll(map);
    };
    //增加
    public void addOneCorporate_equity_structure_design(Corporate_equity_structure_designEntity corporate_equity_structure_designEntity){

    };
    //删除
    public void delCorporate_equity_structure_design(Integer corporate_equity_structure_design_Id){

    };
    //修改
    public void updateCorporate_equity_structure_design(Corporate_equity_structure_designEntity corporate_equity_structure_designEntity){

    };
    //查询总数
    public int selectCorporate_equity_structure_designAllSum(){
       return corporate_equity_structure_designDao.selectCorporate_equity_structure_designAllSum();
    };
}
