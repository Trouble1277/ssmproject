package com.service;

import com.entity.ConferenceEntity;
import com.entity.LegalEntity;

import java.util.List;
import java.util.Map;

public interface LegalService {

    //查询所有
    public List<LegalEntity> selectLegalAll(Map<String, Object> map);
    //增加
    public void addOneLegal(LegalEntity legalEntity);
    //删除
    public void delLegal(Integer Legal_Id);
    //修改
    public void updateLegal(LegalEntity legalEntity);
    //查询总数
    public int selectLegalAllSum();



}
