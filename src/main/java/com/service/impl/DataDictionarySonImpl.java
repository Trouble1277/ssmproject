package com.service.impl;

import com.dao.BlacklistDao;
import com.entity.BlacklistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="BlacklistServiceImpl")
public class BlacklistServiceImpl {
    @Autowired
    BlacklistDao blacklistDao;
    //查询所有
    public List<BlacklistEntity> selectContactsAll(Map<String, Object> map){
        return blacklistDao.selectBlacklistAll(map);
    };
    //增加
    public void addOneBlacklist(BlacklistEntity blacklistEntity){

    };
    //删除
    public void delBlacklist(Integer blacklist_Id){

    };
    //修改
    public void updateBlacklist(BlacklistEntity blacklistEntity){

    };
    //查询总数
    public int selectBlacklistAllSum(){
       return blacklistDao.selectBlacklistAllSum();
    };
}
