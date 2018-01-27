package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.Corporate_financial_documentDao;
import com.entity.Corporate_financial_documentEntity;
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
public class Corporate_financial_documentControl {
    @Autowired
    public Corporate_financial_documentDao corporate_financial_documentDao;

    @RequestMapping("corporateFinancialDocument")
    public String Corporate_financial_documentDaoJsp(){
        return "corporateFinancialDocument";
    }


    @ResponseBody
    @RequestMapping("selectCorporate_financial_documentAll")
    public void selectCorporate_financial_documentAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<Corporate_financial_documentEntity> list=corporate_financial_documentDao.selectCorporate_financial_documentAll(map);
        int total=corporate_financial_documentDao.selectCorporate_financial_documentAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delCorporate_financial_document")
    public void delCorporate_financial_document(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String cfd_financial_document_id=request.getParameter("cfd_financial_document_id");
        try {
            corporate_financial_documentDao.delCorporate_financial_document(Integer.parseInt(cfd_financial_document_id));
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneCorporate_financial_document")
    public void addOneCorporate_financial_document(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        response.setCharacterEncoding("utf-8");

        Corporate_financial_documentEntity corporate_financial_documentEntity=(Corporate_financial_documentEntity)StrTransformObject.StrTransformObject(Corporate_financial_documentEntity.class,request);

        try {
            corporate_financial_documentDao.addOneCorporate_financial_document(corporate_financial_documentEntity);
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateCorporate_financial_document")
    public void updateCorporate_financial_document(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        response.setCharacterEncoding("utf-8");
        Corporate_financial_documentEntity corporate_financial_documentEntity=(Corporate_financial_documentEntity)StrTransformObject.StrTransformObject(Corporate_financial_documentEntity.class,request);
        try {
            corporate_financial_documentDao.updateCorporate_financial_document(corporate_financial_documentEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
