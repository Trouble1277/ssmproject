package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.Lp_fundDao;
import com.entity.ContactsEntity;
import com.entity.Lp_fundEntity;
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
public class Lp_fundControl {
    @Autowired
    public Lp_fundDao lp_fundDao;

    @RequestMapping("lpfund")
    public String ContactsJsp(){
        return "lpfund";
    }


    @ResponseBody
    @RequestMapping("selectLp_fundAll")
    public void selectContactsAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<Lp_fundEntity> list=lp_fundDao.selectLp_fundAll(map);
        int total=lp_fundDao.selectLp_fundAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delLp_fund")
    public void delContacts(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String lp_fund_ribution_Id=request.getParameter("lp_fund_ribution_Id");
        try {
            lp_fundDao.delLp_fund(Integer.parseInt(lp_fund_ribution_Id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneLp_fund")
    public void addOneContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        Lp_fundEntity lp_fundEntity=(Lp_fundEntity)StrTransformObject.StrTransformObject(Lp_fundEntity.class,request);

        try {
            lp_fundDao.addOneLp_fund(lp_fundEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateLp_fund")
    public void updateContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        Lp_fundEntity lp_fundEntity=(Lp_fundEntity)StrTransformObject.StrTransformObject(Lp_fundEntity.class,request);
        try {
            lp_fundDao.updateLp_fund(lp_fundEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
