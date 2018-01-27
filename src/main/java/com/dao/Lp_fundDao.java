package com.dao;

import com.entity.ContactsEntity;
import com.entity.Lp_fundEntity;

import java.util.List;
import java.util.Map;

public interface Lp_fundDao {
   //查询所有
    public List<Lp_fundEntity> selectLp_fundAll(Map<String, Object> map);
    //增加
    public void addOneLp_fund(Lp_fundEntity lp_fundEntity);
    //删除
    public void delLp_fund(Integer lp_fund_Id);
    //修改
    public void updateLp_fund(Lp_fundEntity lp_fundEntity);
    //查询总数
    public int selectLp_fundAllSum();
}
