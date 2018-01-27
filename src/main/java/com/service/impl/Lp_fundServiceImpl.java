package com.service.impl;

import com.dao.Lp_fundDao;
import com.entity.Lp_fundEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="Lp_fundServiceImpl")
public class Lp_fundServiceImpl {
    @Autowired
    Lp_fundDao lp_fundDao;
    //查询所有
    public List<Lp_fundEntity> selectContactsAll(Map<String, Object> map){
        return lp_fundDao.selectLp_fundAll(map);
    };
    //增加
    public void addOneLp_fund(Lp_fundEntity lp_fundEntity){

    };
    //删除
    public void delLp_fund(Integer lp_fund_Id){

    };
    //修改
    public void updateLp_fund(Lp_fundEntity lp_fundEntity){

    };
    //查询总数
    public int selectLp_fundAllSum(){
       return lp_fundDao.selectLp_fundAllSum();
    };
}
