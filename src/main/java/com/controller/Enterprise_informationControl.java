package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.Enterprise_informationDao;
import com.entity.Enterprise_informationEntity;
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
public class Enterprise_informationControl {
    @Autowired
    public Enterprise_informationDao enterprise_informationDao;

    @RequestMapping("enterpriseInformation")
    public String Enterprise_informationJsp(){
        return "enterpriseInformation";
    }


    @ResponseBody
    @RequestMapping("selectEnterprise_informationAll")
    public void selectEnterprise_informationAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<Enterprise_informationEntity> list=enterprise_informationDao.selectEnterprise_informationAll(map);
        int total=enterprise_informationDao.selectEnterprise_informationAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delEnterprise_information")
    public void delEnterprise_information(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String enterprise_id=request.getParameter("enterprise_id");
        try {
            enterprise_informationDao.delEnterprise_information(Integer.parseInt(enterprise_id));
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneEnterprise_information")
    public void addOneEnterprise_information(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        Enterprise_informationEntity enterprise_informationEntity=(Enterprise_informationEntity)StrTransformObject.StrTransformObject(Enterprise_informationEntity.class,request);

        try {
            enterprise_informationDao.addOneEnterprise_information(enterprise_informationEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateEnterprise_information")
    public void updateEnterprise_information(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        Enterprise_informationEntity enterprise_informationEntity=(Enterprise_informationEntity)StrTransformObject.StrTransformObject(Enterprise_informationEntity.class,request);
        try {
            enterprise_informationDao.updateEnterprise_information(enterprise_informationEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
