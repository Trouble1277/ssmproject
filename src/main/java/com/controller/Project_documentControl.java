package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.Project_documentDao;
import com.entity.Project_documentEntity;
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
public class Project_documentControl {
    @Autowired
    public Project_documentDao project_documentDao;

    @RequestMapping("projectDocument")
    public String ContactsJsp(){
        return "projectDocument";
    }


    @ResponseBody
    @RequestMapping("selectProject_documentAll")
    public void selectContactsAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<Project_documentEntity> list=project_documentDao.selectProject_documentAll(map);
        int total=project_documentDao.selectProject_documentAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delProject_document")
    public void delContacts(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String project_documents_id=request.getParameter("project_documents_id");
        try {
            project_documentDao.delProject_document(Integer.parseInt(project_documents_id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneProject_document")
    public void addOneContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        Project_documentEntity project_documentEntity=(Project_documentEntity)StrTransformObject.StrTransformObject(Project_documentEntity.class,request);

        try {
            project_documentDao.addOneProject_document(project_documentEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateProject_document")
    public void updateContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        Project_documentEntity project_documentEntity=(Project_documentEntity)StrTransformObject.StrTransformObject(Project_documentEntity.class,request);
        try {
            project_documentDao.updateProject_document(project_documentEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
