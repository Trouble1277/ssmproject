package com.service;

import com.entity.FundEntity;

import java.util.List;
import java.util.Map;

public interface FundService {
    public List<FundEntity> selectFundAll(Map<String, Object> map);
    public void addOneFund(FundEntity fundEntity);
    public void delFund(Integer fund_Id);
    public void updateFund(FundEntity fundEntity);
    public int selectFundAllSum();
}
