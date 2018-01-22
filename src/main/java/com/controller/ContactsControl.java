package com.controller;

import com.DBUtile.LimitAndSearchUtile;
import com.DBUtile.StrTransformObject;
import com.alibaba.fastjson.JSON;
import com.dao.ContactsDao;
import com.entity.ContactsEntity;
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
public class ContactsControl {
    @Autowired
    public ContactsDao contactsDao;

    @RequestMapping("contacts")
    public String ContactsJsp(){
        return "contacts";
    }


    @ResponseBody
    @RequestMapping("selectContactsAll")
    public void selectContactsAll(HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String,Object> map=LimitAndSearchUtile.sumCondition(request); //分页和模糊查询
        List<ContactsEntity> list=contactsDao.selectContactsAll(map);
        int total=contactsDao.selectContactsAllSum();
        map=new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",total);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @ResponseBody
    @RequestMapping("delContacts")
    public void delContacts(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String contacts_Id=request.getParameter("contacts_Id");
        try {
            contactsDao.delContacts(Integer.parseInt(contacts_Id));
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("addOneContacts")
    public void addOneContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");

        ContactsEntity contactsEntity=(ContactsEntity)StrTransformObject.StrTransformObject(ContactsEntity.class,request);

        try {
            contactsDao.addOneContacts(contactsEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            return;
        }
           response.getWriter().write("{\"success\":\"session\"}");
    }

    @ResponseBody
    @RequestMapping("updateContacts")
    public void updateContacts(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        response.setCharacterEncoding("utf-8");
        ContactsEntity contactsEntity=(ContactsEntity)StrTransformObject.StrTransformObject(ContactsEntity.class,request);
        try {
            contactsDao.updateContacts(contactsEntity);
        }catch (Exception e){
            response.getWriter().write("{\"success\":\"defeated\"}");
            e.printStackTrace();
            return;
        }
        response.getWriter().write("{\"success\":\"session\"}");
    }


}
