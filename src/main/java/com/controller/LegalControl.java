package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.LegalDao;
import com.entity.LegalEntity;
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
public class LegalControl {
    @Autowired
    public LegalDao legalDao;

    @RequestMapping("legal")
    public String LegalDaoJsp(){
        return "legal";
    }


    @ResponseBody
    @RequestMapping("selectLegalAll")
    public void selectLegalAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<LegalEntity> list=legalDao.selectLegalAll(map);
        int total=legalDao.selectLegalAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delLegal")
    public void delLegal(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String legal_id=request.getParameter("legal_id");
        try {
            legalDao.delLegal(Integer.parseInt(legal_id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneLegal")
    public void addOneLegal(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        LegalEntity legalEntity=(LegalEntity)StrTransformObject.StrTransformObject(LegalEntity.class,request);

        try {
            legalDao.addOneLegal(legalEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateLegal")
    public void updateLegal(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        LegalEntity legalEntity=(LegalEntity)StrTransformObject.StrTransformObject(LegalEntity.class,request);
        try {
            legalDao.updateLegal(legalEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
