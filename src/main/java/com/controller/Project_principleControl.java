package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.Project_principleDao;
import com.entity.Project_principleEntity;
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
public class Project_principleControl {
    @Autowired
    public Project_principleDao project_principleDao;

    @RequestMapping("projectPrinciple")
    public String Project_principleDaoJsp(){
        return "projectPrinciple";
    }


    @ResponseBody
    @RequestMapping("selectProject_principleAll")
    public void selectProject_principleAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<Project_principleEntity> list=project_principleDao.selectProject_principleAll(map);
        int total=project_principleDao.selectProject_principleAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delProject_principle")
    public void delProject_principle(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String Project_principle_id=request.getParameter("pp_id");
        try {
            project_principleDao.delProject_principle(Integer.parseInt(Project_principle_id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneProject_principle")
    public void addOneProject_principle(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        response.setCharacterEncoding("utf-8");

        Project_principleEntity project_principleEntity=(Project_principleEntity)StrTransformObject.StrTransformObject(Project_principleEntity.class,request);

        try {
            project_principleDao.addOneProject_principle(project_principleEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateProject_principle")
    public void updateProject_principle(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        response.setCharacterEncoding("utf-8");
        Project_principleEntity project_principleEntity=(Project_principleEntity)StrTransformObject.StrTransformObject(Project_principleEntity.class,request);
        try {
            project_principleDao.updateProject_principle(project_principleEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
