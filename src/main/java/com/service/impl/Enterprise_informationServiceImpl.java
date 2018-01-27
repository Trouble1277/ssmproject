package com.service.impl;

import com.dao.Enterprise_informationDao;
import com.entity.Enterprise_informationEntity;
import com.service.Enterprise_informationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="Enterprise_informationServiceImpl")
public class Enterprise_informationServiceImpl implements Enterprise_informationService{

    @Autowired
    Enterprise_informationDao fd;

    public List<Enterprise_informationEntity> selectEnterprise_informationAll(Map<String,Object> map){
        return fd.selectEnterprise_informationAll(map);
    }
    public void addOneEnterprise_information(Enterprise_informationEntity enterprise_informationEntity){
    };
    public void delEnterprise_information(Integer enterprise_information_Id){
    };
    public void updateEnterprise_information(Enterprise_informationEntity enterprise_informationEntity){

    };
    public int selectEnterprise_informationAllSum(){
        return fd.selectEnterprise_informationAllSum();
    };

}
