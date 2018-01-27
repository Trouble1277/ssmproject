package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.Corporate_visit_recordDao;
import com.entity.Corporate_visit_recordEntity;
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
public class Corporate_visit_recordControl {
    @Autowired
    public Corporate_visit_recordDao corporate_visit_recordDao;

    @RequestMapping("corporateVisitRecord")
    public String Corporate_visit_recordDaoJsp(){
        return "corporateVisitRecord";
    }


    @ResponseBody
    @RequestMapping("selectCorporate_visit_recordAll")
    public void selectCorporate_visit_recordAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<Corporate_visit_recordEntity> list=corporate_visit_recordDao.selectCorporate_visit_recordAll(map);
        int total=corporate_visit_recordDao.selectCorporate_visit_recordAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delCorporate_visit_record")
    public void delCorporate_visit_record(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String cvr_cor_visit_id=request.getParameter("cvr_cor_visit_id");
        try {
            corporate_visit_recordDao.delCorporate_visit_record(Integer.parseInt(cvr_cor_visit_id));
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneCorporate_visit_record")
    public void addOneCorporate_visit_record(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        response.setCharacterEncoding("utf-8");

        Corporate_visit_recordEntity corporate_visit_recordEntity=(Corporate_visit_recordEntity)StrTransformObject.StrTransformObject(Corporate_visit_recordEntity.class,request);

        try {
            corporate_visit_recordDao.addOneCorporate_visit_record(corporate_visit_recordEntity);
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateCorporate_visit_record")
    public void updateCorporate_visit_record(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        response.setCharacterEncoding("utf-8");
        Corporate_visit_recordEntity corporate_visit_recordEntity=(Corporate_visit_recordEntity)StrTransformObject.StrTransformObject(Corporate_visit_recordEntity.class,request);
        try {
            corporate_visit_recordDao.updateCorporate_visit_record(corporate_visit_recordEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
