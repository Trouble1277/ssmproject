package com.dao;

import com.entity.ConferenceEntity;

import java.util.List;
import java.util.Map;

public interface ConferenceDao {

    //查询所有会议列表
    public List<ConferenceEntity> QueryConferenceAll(Map<String,Object> map);

    //增加会议列表
    public void ConFerenceAdd(ConferenceEntity conferenceEntity);

    //修改会议列表
    public void ConFerenceUpdate(ConferenceEntity conferenceEntity);

    //删除会议列表
    public void ConFerenceDel(Integer conference_id);


}
