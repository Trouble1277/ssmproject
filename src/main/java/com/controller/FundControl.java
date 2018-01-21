package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.FundDao;
import com.entity.FundEntity;
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
public class FundControl {
    @Autowired
    public FundDao fundDao;

    @RequestMapping("fund")
    public String fundJsp(){
        return "fund";
    }


    @ResponseBody
    @RequestMapping("selectFundAll")
    public void selectFundAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<FundEntity> list=fundDao.selectFundAll(map);
        int total=fundDao.selectFundAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delFund")
    public void delFund(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String fund_Id=request.getParameter("fund_Id");
        try {
            fundDao.delFund(Integer.parseInt(fund_Id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneFund")
    public void addOneFund(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        FundEntity fundEntity=(FundEntity)StrTransformObject.StrTransformObject(FundEntity.class,request);

        try {
            fundDao.addOneFund(fundEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateFund")
    public void updateFund(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        FundEntity fundEntity=(FundEntity)StrTransformObject.StrTransformObject(FundEntity.class,request);
        try {
            fundDao.updateFund(fundEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
