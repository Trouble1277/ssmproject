package com.controller;

import com.DBUtile.CommUtil;
import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.Activity_modelDao;
import com.entity.Activity_modelEntity;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.test.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Controller
public class ActivityModelControl {
    @Autowired
    public Activity_modelDao activityModelDao;
     //流程
    @Resource
    private RepositoryService repositoryService;

    @RequestMapping("activityModel")
    public String ContactsJsp(){
        return "activityModel";
    }


    @ResponseBody
    @RequestMapping("selectActivityModelAll")
    public void selectContactsAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<Activity_modelEntity> list=activityModelDao.selectActivity_modelAll(map);
        int total=activityModelDao.selectActivity_modelAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delActivityModel")
    public void delContacts(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String lP_Activity_model_id=request.getParameter("activity_model_id");
        try {
            activityModelDao.delActivity_model(Integer.parseInt(lP_Activity_model_id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneActivityModel")
    public void addOneContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        //从classpath路径下读取资源文件.
//        String rootPath = request.getRealPath(CommUtil.uploadParent) + "\\"+"diagrams\tempActivity.zip";
//        InputStream in =new FileInputStream(new File(rootPath));
//        ZipInputStream zipInputStream = new ZipInputStream(in);
//        //获取流程定义和部署对象相关的Service
//        repositoryService.createDeployment()//创建部署对象
//                .addZipInputStream(zipInputStream)//使用zip方式部署，将helloworld.bpmn和helloworld.png压缩成zip格式的文件
//                .deploy();//完成部署

        Activity_modelEntity activity_modelEntity=(Activity_modelEntity)StrTransformObject.StrTransformObject(Activity_modelEntity.class,request);

        try {
            activityModelDao.addOneActivity_model(activity_modelEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateActivityModel")
    public void updateContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        Activity_modelEntity activity_modelEntity=(Activity_modelEntity)StrTransformObject.StrTransformObject(Activity_modelEntity.class,request);
        try {
            activityModelDao.updateActivity_model(activity_modelEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
