package com.service.impl;

import com.dao.DataDictionarySonDao;
import com.entity.DataDictionarySonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="DataDictionarySonServiceImpl")
public class DataDictionarySonImpl {
    @Autowired
    DataDictionarySonDao DataDictionarySonDao;
    //查询所有
    public List<DataDictionarySonEntity> selectContactsAll(Map<String, Object> map){
        return DataDictionarySonDao.selectDataDictionarySonAll(map);
    };
    //增加
    public void addOneDataDictionarySon(DataDictionarySonEntity dataDictionarySonEntity){

    };
    //删除
    public void delDataDictionarySon(Integer dataDictionarySon_Id){

    };
    //修改
    public void updateDataDictionarySon(DataDictionarySonEntity dataDictionarySonEntity){

    };
    //查询总数
    public int selectDataDictionarySonAllSum(){
       return DataDictionarySonDao.selectDataDictionarySonAllSum();
    };
}
