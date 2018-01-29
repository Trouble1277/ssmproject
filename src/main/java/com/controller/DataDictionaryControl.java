package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.DataDictionaryDao;
import com.entity.DataDictionaryEntity;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataDictionaryControl {
    @Autowired
    public DataDictionaryDao backlistDao;

    @RequestMapping("dataDictionary")
    public String ContactsJsp(){
        return "dataDictionary";
    }

    @ResponseBody
    @RequestMapping("selectDataDictionaryOneSon")
    public void selectDataDictionaryOneSon(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String ddid=request.getParameter("ddid");
        Map<String,Object> map=new HashMap<String, Object>();
        DataDictionaryEntity  dda=null;
        if(!StringUtils.isNullOrEmpty(ddid)){
            dda=backlistDao.selectDataDictionaryOneSon(Integer.parseInt(ddid));
        }
//        map.put("rows",list);
//        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(dda));
    }

    @ResponseBody
    @RequestMapping("selectDataDictionaryAll")
    public void selectContactsAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<DataDictionaryEntity> list=backlistDao.selectDataDictionaryAll(map);
        int total=backlistDao.selectDataDictionaryAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delDataDictionary")
    public void delContacts(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String ddid=request.getParameter("ddid");
        try {
            backlistDao.delDataDictionary(Integer.parseInt(ddid));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneDataDictionary")
    public void addOneContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        DataDictionaryEntity dataDictionaryEntity=(DataDictionaryEntity)StrTransformObject.StrTransformObject(DataDictionaryEntity.class,request);

        try {
            backlistDao.addOneDataDictionary(dataDictionaryEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateDataDictionary")
    public void updateContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        DataDictionaryEntity dataDictionaryEntity=(DataDictionaryEntity)StrTransformObject.StrTransformObject(DataDictionaryEntity.class,request);
        try {
            backlistDao.updateDataDictionary(dataDictionaryEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
