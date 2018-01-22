package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.Corporate_equity_structure_designDao;
import com.entity.Corporate_equity_structure_designEntity;
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
public class Corporate_equity_structure_designControl {
    @Autowired
    public Corporate_equity_structure_designDao corporate_equity_structure_designDao;

    @RequestMapping("corporateEquityStructureDesign")
    public String Corporate_equity_structure_designJsp(){
        return "corporateEquityStructureDesign";
    }


    @ResponseBody
    @RequestMapping("selectCorporate_equity_structure_designAll")
    public void selectCorporate_equity_structure_designAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<Corporate_equity_structure_designEntity> list=corporate_equity_structure_designDao.selectCorporate_equity_structure_designAll(map);
        int total=corporate_equity_structure_designDao.selectCorporate_equity_structure_designAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delCorporate_equity_structure_design")
    public void delCorporate_equity_structure_design(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String cesd_cor_equity_id=request.getParameter("cesd_cor_equity_id");
        try {
            corporate_equity_structure_designDao.delCorporate_equity_structure_design(Integer.parseInt(cesd_cor_equity_id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneCorporate_equity_structure_design")
    public void addOneCorporate_equity_structure_design(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        Corporate_equity_structure_designEntity corporate_equity_structure_designEntity=(Corporate_equity_structure_designEntity)StrTransformObject.StrTransformObject(Corporate_equity_structure_designEntity.class,request);

        try {
            corporate_equity_structure_designDao.addOneCorporate_equity_structure_design(corporate_equity_structure_designEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateCorporate_equity_structure_design")
    public void updateCorporate_equity_structure_design(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        Corporate_equity_structure_designEntity corporate_equity_structure_designEntity=(Corporate_equity_structure_designEntity)StrTransformObject.StrTransformObject(Corporate_equity_structure_designEntity.class,request);
        try {
            corporate_equity_structure_designDao.updateCorporate_equity_structure_design(corporate_equity_structure_designEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
