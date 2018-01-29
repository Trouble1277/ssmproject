package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.ProjectDao;
import com.entity.ProjectEntity;
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
public class ProjectControl {
    @Autowired
    public ProjectDao projectDao;

    @RequestMapping("project")
    public String ProjectJsp(){
        return "project";
    }


    @ResponseBody
    @RequestMapping("selectProjectAll")
    public void selectProjectAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<ProjectEntity> list=projectDao.selectProjectAll(map);
        int total=projectDao.selectProjectAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delProject")
    public void delProject(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String Project_Id=request.getParameter("project_id");
        try {
            projectDao.delProject(Integer.parseInt(Project_Id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneProject")
    public void addOneProject(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        ProjectEntity ProjectEntity=(ProjectEntity)StrTransformObject.StrTransformObject(ProjectEntity.class,request);

        try {
            projectDao.addOneProject(ProjectEntity);
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateProject")
    public void updateProject(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        ProjectEntity ProjectEntity=(ProjectEntity)StrTransformObject.StrTransformObject(ProjectEntity.class,request);
        try {
            projectDao.updateProject(ProjectEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
