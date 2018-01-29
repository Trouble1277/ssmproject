package com.service.impl;

import com.dao.DataDictionaryDao;
import com.entity.DataDictionaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="DataDictionaryServiceImpl")
public class DataDictionaryImpl {
    @Autowired
    DataDictionaryDao dataDictionaryDao;
    //查询所有
    public List<DataDictionaryEntity> selectContactsAll(Map<String, Object> map){
        return dataDictionaryDao.selectDataDictionaryAll(map);
    };
    //增加
    public void addOneDataDictionary(DataDictionaryEntity dataDictionaryEntity){

    };
    //删除
    public void delDataDictionary(Integer dataDictionary_Id){

    };
    //修改
    public void updateDataDictionary(DataDictionaryEntity dataDictionaryEntity){

    };
    //查询总数
    public int selectDataDictionaryAllSum(){
       return dataDictionaryDao.selectDataDictionaryAllSum();
    };

    //查询单个下拉宽
    public DataDictionaryEntity selectDataDictionaryOneSon(Integer ddid){
        return dataDictionaryDao.selectDataDictionaryOneSon(ddid);
    };
}
