package com.service.impl;

import com.dao.Corporate_financial_documentDao;
import com.entity.Corporate_financial_documentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="Corporate_financial_documentServiceImpl")
public class Corporate_financial_documentServiceImpl {
    @Autowired
    Corporate_financial_documentDao corporate_financial_documentDao;
    //查询所有
    public List<Corporate_financial_documentEntity> selectCorporate_financial_documentAll(Map<String, Object> map){
        return corporate_financial_documentDao.selectCorporate_financial_documentAll(map);
    };
    //增加
    public void addOneCorporate_financial_document(Corporate_financial_documentEntity corporate_financial_documentEntity){

    };
    //删除
    public void delCorporate_financial_document(Integer corporate_financial_document_Id){

    };
    //修改
    public void updateCorporate_financial_document(Corporate_financial_documentEntity corporate_financial_documentEntity){

    };
    //查询总数
    public int selectCorporate_financial_documentAllSum(){
       return corporate_financial_documentDao.selectCorporate_financial_documentAllSum();
    };
}
