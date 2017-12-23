package com.controller;
import com.alibaba.fastjson.JSON;
import com.entity.UserEntity;
import com.service.UserService;
import com.vo.UserAuthorVo;
import com.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    //进入到login页面
    @RequestMapping("login")
    public String Login(){

        return "login";
    }

    //进入到index页面
    @RequestMapping("index")
    public String Index(){

        return "index";
    }

    @RequestMapping("home")
    public String Home(){

        return "homeManage";
    }

    @RequestMapping("userManage")
    public String userManage(){

        return "userManage";
    }

    @RequestMapping("roleManage")
    public String roleManage(){

        return "roleManage";
    }

    @RequestMapping("authorManage")
    public String authorManage(){

        return "authorManage";
    }



    //登录
   // @ResponseBody
    @RequestMapping("UserLogin")
    public void UserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session= request.getSession();
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        UserEntity userEntity=new UserEntity();
        userEntity.setLoginName(loginName);
        userEntity.setPassword(password);
        UserEntity userEntityResult=userService.userLogin(userEntity);
        if (userEntityResult!=null){
            session.setAttribute("user",userEntityResult);
            response.getWriter().write("{\"success\":\"success\"}");
        }else{
            response.getWriter().write("{\"error\":\"error\"}");
        }


    }


    //查询手风琴
    @RequestMapping("MenuQueryLeft" )
    public void MenuQueryLeft(String menuPid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        List<UserAuthorVo> userAuthorVoList=userService.UserQueryAuthorAccordion(user.getUserId());
        List<UserRoleVo> userRoleVoList=new ArrayList<UserRoleVo>();

        if (userAuthorVoList.size()==0){
            userRoleVoList=userService.UserQueryRoleAccordion(user.getUserId());
            response.getWriter().write(JSON.toJSONString(userRoleVoList));
        }else {
            response.getWriter().write(JSON.toJSONString(userAuthorVoList));
        }

    }


    //查询所有用户
    @RequestMapping("queryUserAll")
    public void QueryUserAll(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        List<UserEntity> userEntityList=userService.queryUserAll();
        response.getWriter().write(JSON.toJSONString(userEntityList));
    }




public void Test(){

    System.out.println("JminIcon");
    System.out.println("Trouble");
}



}
