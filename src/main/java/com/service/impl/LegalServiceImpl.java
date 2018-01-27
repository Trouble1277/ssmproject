package com.service.impl;

import com.dao.LegalDao;
import com.entity.LegalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="LegalServiceImpl")
public class LegalServiceImpl {
    @Autowired
    LegalDao LegalDao;
    //查询所有
    public List<LegalEntity> selectLegalAll(Map<String, Object> map){
        return LegalDao.selectLegalAll(map);
    };
    //增加
    public void addOneLegal(LegalEntity legalEntity){

    };
    //删除
    public void delLegal(Integer legal_Id){

    };
    //修改
    public void updateLegal(LegalEntity legalEntity){

    };
    //查询总数
    public int selectLegalAllSum(){
       return LegalDao.selectLegalAllSum();
    };
}
