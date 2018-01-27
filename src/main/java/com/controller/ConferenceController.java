package com.controller;

import com.entity.ConferenceEntity;
import com.entity.FundEntity;
import com.entity.Limits;
import com.service.ConferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public Map<String,Object> QueryConferenceAllResult(ConferenceEntity conferenceEntity, HttpServletRequest request){
        String reqStart = request.getParameter("index");
        String reqLimit = request.getParameter("size");
        Integer start = 0;
        Integer limit = 10;
        if(!StringUtils.isEmpty(reqStart)){
            start = Integer.parseInt(reqStart);
        }
        if(!StringUtils.isEmpty(reqLimit)){
            limit = Integer.parseInt(reqLimit);
        }

//        System.out.println(start);
//        System.out.println(limit);
        Map<String,Object> map = new HashMap<String,Object>();
        Limits limits = new Limits(start,limit);
        String conference_emcee = conferenceEntity.getConference_emcee();
        if(!StringUtils.isEmpty(conference_emcee)){
            try {
                conference_emcee = new String(conference_emcee.getBytes("ISO-8859-1"),"utf-8");
                System.out.println(conference_emcee);
            }catch (Exception e){
               e.printStackTrace();
            }
        }
        conferenceEntity.setConference_emcee(conference_emcee);
        map.put("cf",conferenceEntity);
        map.put("limits",limits);
        List<ConferenceEntity> list = conferenceService.QueryConferenceAll(map);
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
            return "{\"sucess\":\"\"}";
        }
    }


    @ResponseBody
    @RequestMapping("AddConference")
    public String AddConference(HttpServletRequest request,ConferenceEntity conferenceEntity){
        System.out.println(request.getParameter("conference_document"));
        System.out.println(conferenceEntity.toString());
        try {
            conferenceService.ConFerenceAdd(conferenceEntity);
            return "{\"sucess\":\"sucess\"}";
        }catch (Exception e){
            return "{\"sucess\":\"\"}";
        }
    }


    @ResponseBody
    @RequestMapping("DelConference")
    public String DelConference(String conference_id){
        try {
            conferenceService.ConFerenceDel(Integer.parseInt(conference_id));
            return "{\"sucess\":\"sucess\"}";
        }catch (Exception e){
            return "{\"sucess\":}";
        }
    }


    @ResponseBody
    @RequestMapping("ConferenceFundAll")
    public List<FundEntity> FundAll(){
        return conferenceService.QueryConferenceFund();
    }


}
