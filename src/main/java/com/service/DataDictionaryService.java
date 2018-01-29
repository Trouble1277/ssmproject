package com.service;

import com.entity.DataDictionaryEntity;

import java.util.List;
import java.util.Map;

public interface DataDictionaryService {
 //查询所有
 public List<DataDictionaryEntity> selectDataDictionaryAll(Map<String, Object> map);
 //增加
 public void addOneDataDictionary(DataDictionaryEntity dataDictionaryEntity);
 //删除
 public void delDataDictionary(Integer dataDictionary_Id);
 //修改
 public void updateDataDictionary(DataDictionaryEntity dataDictionaryEntity);
 //查询总数
 public int selectDataDictionaryAllSum();
 //查询单个下拉宽
 public DataDictionaryEntity selectDataDictionaryOneSon(Integer ddid);
}
