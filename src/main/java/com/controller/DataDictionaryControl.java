package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.DataDictionarySonDao;
import com.entity.DataDictionarySonEntity;
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
public class DataDictionarySonControl {
    @Autowired
    public DataDictionarySonDao backlistDao;

    @RequestMapping("dataDictionarySon")
    public String ContactsJsp(){
        return "dataDictionarySon";
    }


    @ResponseBody
    @RequestMapping("selectDataDictionarySonAll")
    public void selectContactsAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<DataDictionarySonEntity> list=backlistDao.selectDataDictionarySonAll(map);
        int total=backlistDao.selectDataDictionarySonAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delDataDictionarySon")
    public void delContacts(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String ddsonid=request.getParameter("ddsonid");
        try {
            backlistDao.delDataDictionarySon(Integer.parseInt(ddsonid));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneDataDictionarySon")
    public void addOneContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        DataDictionarySonEntity dataDictionarySonEntity=(DataDictionarySonEntity)StrTransformObject.StrTransformObject(DataDictionarySonEntity.class,request);

        try {
            backlistDao.addOneDataDictionarySon(dataDictionarySonEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateDataDictionarySon")
    public void updateContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        DataDictionarySonEntity dataDictionarySonEntity=(DataDictionarySonEntity)StrTransformObject.StrTransformObject(DataDictionarySonEntity.class,request);
        try {
            backlistDao.updateDataDictionarySon(dataDictionarySonEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
