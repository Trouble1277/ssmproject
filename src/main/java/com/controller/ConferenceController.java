package com.controller;

import com.entity.ConferenceEntity;
import com.entity.Limits;
import com.service.ConferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConferenceController {



    @Resource
    private ConferenceService conferenceService;

    public ConferenceController() {
    }

    @RequestMapping("conferenceManage")
    public String conferenceManage(){
       return "conferenceManage";
    }


    @ResponseBody
    @RequestMapping(value = "QueryConferenceAllResult")
    public Map<String,Object> QueryConferenceAllResult(ConferenceEntity conferenceEntity){
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
        Map<String,Object> maps = new HashMap<String,Object>();
        maps.put("rows",list);
        maps.put("total",list.size());
        return maps;
    }


    @ResponseBody
    @RequestMapping("UpdateConference")
    public String UpdateConference(ConferenceEntity conferenceEntity){
        try {
            conferenceService.ConFerenceUpdate(conferenceEntity);
            return "{\"sucess\":\"sucess\"}";
        }catch (Exception e){
            return "{\"sucess\"}";
        }
    }


    @ResponseBody
    @RequestMapping("AddConference")
    public String AddConference(ConferenceEntity conferenceEntity){
        try {
            conferenceService.ConFerenceAdd(conferenceEntity);
            return "{\"sucess\":\"sucess\"}";
        }catch (Exception e){
            return "{\"sucess\"}";
        }
    }


    @ResponseBody
    @RequestMapping("DelConference")
    public String DelConference(String conference_id){
        try {
            conferenceService.ConFerenceDel(Integer.parseInt(conference_id));
            return "{\"sucess\":\"sucess\"}";
        }catch (Exception e){
            return "{\"sucess\"}";
        }
    }


}
