package com.service;

import com.entity.ConferenceEntity;
import com.entity.FundEntity;

import java.util.List;
import java.util.Map;

public interface ConferenceService {



    //查询所有会议列表
    public List<ConferenceEntity> QueryConferenceAll(Map<String,Object> map);

    //增加会议列表
    public void ConFerenceAdd(ConferenceEntity conferenceEntity);

    //修改会议列表
    public void ConFerenceUpdate(ConferenceEntity conferenceEntity);

    //删除会议列表
    public void ConFerenceDel(Integer conference_id);

    public List<FundEntity> QueryConferenceFund();
}
