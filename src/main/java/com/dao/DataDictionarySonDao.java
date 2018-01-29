package com.dao;

import com.entity.DataDictionarySonEntity;

import java.util.List;
import java.util.Map;

public interface DataDictionarySonDao {
   //查询所有
    public List<DataDictionarySonEntity> selectDataDictionarySonAll(Map<String, Object> map);
    //增加
    public void addOneDataDictionarySon(DataDictionarySonEntity dataDictionarySonEntity);
    //删除
    public void delDataDictionarySon(Integer dataDictionarySon_Id);
    //修改
    public void updateDataDictionarySon(DataDictionarySonEntity dataDictionarySonEntity);
    //查询总数
    public int selectDataDictionarySonAllSum();
}
