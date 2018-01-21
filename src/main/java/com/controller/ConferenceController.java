package com.controller;

import com.entity.ConferenceEntity;
import com.entity.Limits;
import com.service.ConferenceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConferenceController {


    //log4j打印
    private  Logger logger = LogManager.getLogger(ConferenceController.class);

    @Resource
    private ConferenceService conferenceService;

    public ConferenceController() {
    }

    @ResponseBody
    @RequestMapping(value = "QueryConferenceAllResult")
    public List<ConferenceEntity> QueryConferenceAllResult(ConferenceEntity conferenceEntity){
        Map<String,Object> map = new HashMap<String,Object>();
        Limits limits = new Limits(0,1);
        String conference_emcee = conferenceEntity.getConference_emcee();
        try {
            conference_emcee = new String(conference_emcee.getBytes("ISO-8859-1"),"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        conferenceEntity.setConference_emcee(conference_emcee);
        map.put("cf",conferenceEntity);
        map.put("limits",limits);
        List<ConferenceEntity> list = conferenceService.QueryConferenceAll(map);
//        logger.debug(list.get(0).getConference_emcee());
        return list;
    }




}
