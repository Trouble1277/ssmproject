package com.service;

import com.entity.Enterprise_informationEntity;

import java.util.List;
import java.util.Map;

public interface Enterprise_informationService {
    public List<Enterprise_informationEntity> selectEnterprise_informationAll(Map<String, Object> map);
    public void addOneEnterprise_information(Enterprise_informationEntity enterprise_informationEntity);
    public void delEnterprise_information(Integer enterprise_information_Id);
    public void updateEnterprise_information(Enterprise_informationEntity enterprise_informationEntity);
    public int selectEnterprise_informationAllSum();
}
