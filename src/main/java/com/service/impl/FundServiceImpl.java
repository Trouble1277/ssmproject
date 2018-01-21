package com.service.impl;

import com.dao.FundDao;
import com.entity.FundEntity;
import com.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="FundServiceImpl")
public class FundServiceImpl implements FundService{

    @Autowired
    FundDao fd;

    public List<FundEntity> selectFundAll(Map<String,Object> map){
        return fd.selectFundAll(map);
    }
    public void addOneFund(FundEntity fundEntity){
    };
    public void delFund(Integer fund_Id){
    };
    public void updateFund(FundEntity fundEntity){

    };
    public int selectFundAllSum(){
        return fd.selectFundAllSum();
    };

}
