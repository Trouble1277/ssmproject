package com.service;

import com.entity.Corporate_financial_documentEntity;

import java.util.List;
import java.util.Map;

public interface Corporate_financial_documentService {

    //查询所有
    public List<Corporate_financial_documentEntity> selectCorporate_financial_documentAll(Map<String, Object> map);
    //增加
    public void addOneCorporate_financial_document(Corporate_financial_documentEntity corporate_financial_documentEntity);
    //删除
    public void delCorporate_financial_document(Integer corporate_financial_document_Id);
    //修改
    public void updateCorporate_financial_document(Corporate_financial_documentEntity corporate_financial_documentEntity);
    //查询总数
    public int selectCorporate_financial_documentAllSum();



}
