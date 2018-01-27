package com.service;

import com.entity.BlacklistEntity;

import java.util.List;
import java.util.Map;

public interface BlacklistService {
   //查询所有
    public List<BlacklistEntity> selectBlacklistAll(Map<String, Object> map);
    //增加
    public void addOneBlacklist(BlacklistEntity blacklistEntity);
    //删除
    public void delBlacklist(Integer blacklist_Id);
    //修改
    public void updateBlacklist(BlacklistEntity blacklistEntity);
    //查询总数
    public int selectBlacklistAllSum();
}
