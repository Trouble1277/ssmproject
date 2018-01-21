package com.service.impl;

import com.dao.ConferenceDao;
import com.entity.ConferenceEntity;
import com.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    ConferenceDao conferenceDao;



    public List<ConferenceEntity> QueryConferenceAll(Map<String,Object> map) {
        return conferenceDao.QueryConferenceAll(map);
    }

    public void ConFerenceAdd(ConferenceEntity conferenceEntity) {
        conferenceDao.ConFerenceAdd(conferenceEntity);
    }

    public void ConFerenceUpdate(ConferenceEntity conferenceEntity) {
        conferenceDao.ConFerenceUpdate(conferenceEntity);
    }

    public void ConFerenceDel(Integer conference_id) {
        conferenceDao.ConFerenceDel(conference_id);
    }


}
