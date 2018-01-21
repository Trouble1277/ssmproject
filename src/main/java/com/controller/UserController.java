package com.controller;
import com.alibaba.fastjson.JSON;
import com.entity.*;
import com.service.UserService;
import com.sun.org.apache.bcel.internal.generic.I2F;
import com.vo.UserRoleAuthorVo;
import com.vo.UserAuthorVo;
import com.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");//获取登录的用户

        List<UserAuthorVo> userAuthorVoList=userService.queryUserAllAuthor(user.getUserId());
        response.getWriter().write(JSON.toJSONString(userAuthorVoList));
//        List<UserAuthorVo> userAuthorVoList=userService.UserQueryAuthorAccordion(user.getUserId());
//        List<UserRoleVo> userRoleVoList=new ArrayList<UserRoleVo>();
//
//        if (userAuthorVoList.size()==0){
//            userRoleVoList=userService.UserQueryRoleAccordion(user.getUserId());
//            response.getWriter().write(JSON.toJSONString(userRoleVoList));
//        }else {
//            response.getWriter().write(JSON.toJSONString(userAuthorVoList));
//        }

    }


    //查询所有用户
    @RequestMapping("queryUserAll")
    public void QueryUserAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
//        String order= request.getParameter("order");
        String offset= request.getParameter("index");
        String limit= request.getParameter("size");

        Map<String,Object> parm=new HashMap<String,Object>();
        Map<String,Object> map=new HashMap<String,Object>();
        if (offset != null && limit != null) {

            parm.put("start", offset);
            parm.put("end", offset+limit);
        }
//        if (order != null && order != null) {
//            parm.put("order", order);
//        }

        List<UserEntity> userEntityList=userService.queryUserAll(parm);
        int total=userService.queryUserAllCount(parm);
        List<UserRoleAuthorVo> userRoleAuthorVoList =new ArrayList<UserRoleAuthorVo>();
        for (UserEntity entity : userEntityList) {
            String name="";//用户的所有权限
            String roleName="";//用户的所有的角色
            String userAuthorName="";//用户对应的权限
            UserRoleAuthorVo userRoleAuthorVo =new UserRoleAuthorVo();
            userRoleAuthorVo.setUserId(entity.getUserId());
            userRoleAuthorVo.setLoginName(entity.getLoginName());
            userRoleAuthorVo.setPassword(entity.getPassword());
            userRoleAuthorVo.setUserName(entity.getUserName());
            userRoleAuthorVo.setPhoneNumber(entity.getPhoneNumber());
            userRoleAuthorVo.setEmail(entity.getEmail());
            userRoleAuthorVo.setLoginName(entity.getLoginName());
            userRoleAuthorVo.setCreateTime(entity.getCreateTime());
            userRoleAuthorVo.setRoleEntityList(entity.getRoleEntityList());

            //查询用户的角色
            List<UserRoleVo> userRoleVoList=userService.queryUserRole(entity.getUserId());
            for (UserRoleVo userRoleVo : userRoleVoList) {
                roleName+=userRoleVo.getRoleName()+",";
            }

            //查询用户所有的权限
            List<UserAuthorVo> userAuthorVoList=userService.queryUserAllAuthor(entity.getUserId());
            for (UserAuthorVo userAuthorVo : userAuthorVoList) {
                name+= userAuthorVo.getName()+",";
            }
            //查询用户直接对应的权限
            List<UserAuthorVo> authorVos=userService.UserQueryAuthorAccordion(entity.getUserId());
            for (UserAuthorVo authorVo : authorVos) {
                userAuthorName+=authorVo.getName()+",";
            }


//            //查询角色对权限
//            if (userAuthorName==""){
//                List<UserRoleVo> userRoleVoList=userService.UserQueryRoleAccordion(entity.getUserId());
//                for (UserRoleVo userRoleVo : userRoleVoList) {
//                    if (userRoleVo.getName()==""||userRoleVo==null){
//                        userAuthorName+="该身份没有权限,";
//                    }
//                    userAuthorName+= userRoleVo.getName()+",";
//                }
//            }
            //截取后面多余的，
            if (name.length()!=0){
                name=name.substring(0,name.length()-1);
            }
            if (userAuthorName.length()!=0){
                userAuthorName=userAuthorName.substring(0,userAuthorName.length()-1);
            }
            if (roleName.length()!=0){
                roleName= roleName.substring(0,roleName.length()-1);
            }

            userRoleAuthorVo.setName(name);
            userRoleAuthorVo.setUserAuthorName(userAuthorName);
            userRoleAuthorVo.setRoleName(roleName);
            userRoleAuthorVoList.add(userRoleAuthorVo);
        }
//        for (UserRoleAuthorVo userRoleAuthorVo : userRoleAuthorVoList) {
//            userRoleAuthorVo.setRoleEntityList(null);
//        }
        map.put("rows",userRoleAuthorVoList);
        map.put("total",total);



        response.getWriter().write(JSON.toJSONString(map));
    }

    //增加用户
    @RequestMapping("addUser")
    public void AddUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String user=request.getParameter("user");
        String author=request.getParameter("atuhorArray");
        String role=request.getParameter("role");
        String[] roles= role.split(",");
        Object object=JSON.parseObject(user);
        Map<String,Object> UserMap= (Map<String, Object>) object;
        UserEntity userEntity=new UserEntity();
        String uid="";
        for (Object key:UserMap.keySet()){
           if (key=="loginName"){
                userEntity.setLoginName((String) UserMap.get(key));
            } if (key=="password"){
                userEntity.setPassword((String) UserMap.get(key));
            } if (key=="userName"){
                userEntity.setUserName((String) UserMap.get(key));
            }if (key=="phoneNumber"){
                userEntity.setPhoneNumber((String) UserMap.get(key));
            }if (key=="email"){
                userEntity.setEmail((String) UserMap.get(key));
            }if (key=="createTime"){
                userEntity.setCreateTime((String) UserMap.get(key));
            }
        }

        Integer authorId=null;
        String authorMenu="";
        UserEntity userEntityUA=new UserEntity();
        AuthorEntity authorEntityUA=null;
        try{
            //增加一个用户
            int n=userService.addUser(userEntity);
            uid=userEntity.getUserId()+"";

            userEntityUA.setUserId(Integer.parseInt(uid));
            if (n>0){
                //增加用户的所有角色
                if (role != "") {
                    for (int i=0;i<roles.length;i++){
                        UserRoleEntity userRoleEntity=new UserRoleEntity();
                        UserEntity userEntityAdd=new UserEntity();
                        RoleEntity roleEntityAdd=new RoleEntity();
                        userEntityAdd.setUserId(Integer.parseInt(uid));
                        roleEntityAdd.setRoleId(Integer.parseInt(roles[i]));
                        userRoleEntity.setUserEntity(userEntityAdd);
                        userRoleEntity.setRoleEntity(roleEntityAdd);
                        int AddUserRoleN=userService.addUserRole(userRoleEntity);
                    }
                }



                List<Object> list=JSON.parseArray(author);
                for (Object o:list){
                    UserAuthorEntity userAuthorEntity=new UserAuthorEntity();
                    Map<String,Object> AuthorMap= (Map<String, Object>) o;
                    for (Object key:AuthorMap.keySet()){
                        userAuthorEntity.setUserEntity(userEntityUA);//每循环对象的时候把用户设置进去
                        authorId=null;
                        authorMenu="";
                        if (key=="Author"){
                            authorEntityUA=new AuthorEntity();
                            authorId= (Integer) AuthorMap.get(key);
                            authorEntityUA.setId(authorId);
                        }else if (key=="AuthorMenu"){
                            authorMenu= (String) AuthorMap.get(key);
                            userAuthorEntity.setAuthorMenu(authorMenu);//按钮
                            userAuthorEntity.setAuthorEntity(authorEntityUA);//设置权限
                        }
                    }
                    //增加用户直接对应的权限以及按钮
                    int UAAddN=userService.addUserAuthor(userAuthorEntity);
                }
            }

            response.getWriter().write("{\"success\":\"success\"}");
        }catch (Exception e){
            response.getWriter().write("{\"error\":\"error\"}");
            e.printStackTrace();
        }


    }


    //修改用户
    @RequestMapping("updateUser")
    public void UpdateUser(HttpServletRequest request,HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        String user=request.getParameter("user");
        String author=request.getParameter("atuhorArray");
        String role=request.getParameter("role");

        Object object=JSON.parseObject(user);
        Map<String,Object> UserMap= (Map<String, Object>) object;
        UserEntity userEntity=new UserEntity();
        String uid="";
        for (Object key:UserMap.keySet()){
            if (key=="userId"){
                uid= (String) UserMap.get(key);
                userEntity.setUserId(Integer.parseInt(uid));
            } if (key=="loginName"){
                userEntity.setLoginName((String) UserMap.get(key));
            } if (key=="password"){
                userEntity.setPassword((String) UserMap.get(key));
            } if (key=="userName"){
                userEntity.setUserName((String) UserMap.get(key));
            }if (key=="phoneNumber"){
                userEntity.setPhoneNumber((String) UserMap.get(key));
            }if (key=="email"){
                userEntity.setEmail((String) UserMap.get(key));
            }
        }
        Integer authorId=null;
        String authorMenu="";
        UserEntity userEntityUA=new UserEntity();
        userEntityUA.setUserId(Integer.parseInt(uid));
        AuthorEntity authorEntityUA=null;
        try{
            int n=userService.updateUser(userEntity);

            if (n>0){
                //删除用户的所有角色
                int DelUserRoleN=userService.delUserRole(Integer.parseInt(uid));
                if (role != "") {
                    String[] roles= role.split(",");

                    for (int i=0;i<roles.length;i++){//增加用户的所有角色
                        UserRoleEntity userRoleEntity=new UserRoleEntity();
                        UserEntity userEntityAdd=new UserEntity();
                        RoleEntity roleEntityAdd=new RoleEntity();
                        userEntityAdd.setUserId(Integer.parseInt(uid));
                        roleEntityAdd.setRoleId(Integer.parseInt(roles[i]));
                        userRoleEntity.setUserEntity(userEntityAdd);
                        userRoleEntity.setRoleEntity(roleEntityAdd);
                        int AddUserRoleN=userService.addUserRole(userRoleEntity);//将数据在增加进去
                    }
                }



                int UADelN=userService.delUserAuthor(Integer.parseInt(uid));//先删除中间表的数据然后在增加
                List<Object> list=JSON.parseArray(author);
                for (Object o:list){
                    UserAuthorEntity userAuthorEntity=new UserAuthorEntity();
                    Map<String,Object> AuthorMap= (Map<String, Object>) o;
                    for (Object key:AuthorMap.keySet()){
                        userAuthorEntity.setUserEntity(userEntityUA);//每循环对象的时候把用户设置进去
                        authorId=null;
                        authorMenu="";
                        if (key=="Author"){
                            authorEntityUA=new AuthorEntity();
                            authorId= (Integer) AuthorMap.get(key);
                            authorEntityUA.setId(authorId);

                        }else if (key=="AuthorMenu"){
                            authorMenu= (String) AuthorMap.get(key);
                            userAuthorEntity.setAuthorMenu(authorMenu);
                            userAuthorEntity.setAuthorEntity(authorEntityUA);//设置权限

                        }
                    }
                    int UAAddN=userService.addUserAuthor(userAuthorEntity);
                }
            }

            response.getWriter().write("{\"success\":\"success\"}");
        }catch (Exception e){
            response.getWriter().write("{\"error\":\"error\"}");
            e.printStackTrace();
        }
    }


    //删除用户
    @RequestMapping("delUser")
    public void delUser(HttpServletRequest request,HttpServletResponse response)throws IOException{
        String userId=request.getParameter("userId");

        try{
            int n=userService.delUser(Integer.parseInt(userId));
            response.getWriter().write("{\"success\":\"success\"}");
        }catch (Exception e){
            response.getWriter().write("{\"error\":\"error\"}");
            e.printStackTrace();
        }

    }

    //查询ztrre权限
    @RequestMapping("queryAuthorAll")
    public void QueryAuthorAll(HttpServletResponse response,String id) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        List<AuthorEntity> authorEntities=null;
        if (id==null){
            authorEntities=userService.queryAuthorAll(0);
        }else{
            authorEntities=userService.queryAuthorAll(Integer.parseInt(id));
        }

        response.getWriter().write(JSON.toJSONString(authorEntities));
    }

    //根据id查权限名称
    @RequestMapping("queryAuthorById")
    public void queryAuthorById(HttpServletResponse response,String id) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");


            AuthorEntity authorEntity=userService.queryAuthorById(Integer.parseInt(id));

        response.getWriter().write(JSON.toJSONString(authorEntity));
    }


    //查询权限
    @RequestMapping("queryAuthorAllManage")
    public void queryAuthorAllManage(HttpServletResponse response,HttpServletRequest request) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String offset= request.getParameter("index");
        String limit= request.getParameter("size");
        String id=request.getParameter("id");


        Map<String,Object> parm=new HashMap<String,Object>();
        Map<String,Object> map=new HashMap<String,Object>();
        if (!StringUtils.isEmpty(id)){
            parm.put("id",id);
        }
        if (offset != null && limit != null) {
            parm.put("start", offset);
            parm.put("end", offset+limit);
        }
        List<AuthorEntity> authorEntities=userService.queryAuthorAllManage(parm);

        map.put("total",userService.queryAuthorAllManageCount(parm));
        map.put("rows",authorEntities);


        response.getWriter().write(JSON.toJSONString(map));
    }


    //增加权限
    @RequestMapping("addAuthor")
    public void AddAuthor(HttpServletRequest request,HttpServletResponse response,AuthorEntity authorEntity) throws IOException {
        request.setCharacterEncoding("utf-8");

        int n=userService.addAuthor(authorEntity);
        if (n>0){
            response.getWriter().write("{\"success\":\"success\"}");
        }else{
            response.getWriter().write("{\"error\":\"error\"}");
        }

    }



    //删除权限
    @RequestMapping("delAuthor")
    public void DelAuthor(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String id=request.getParameter("id");

        int n=userService.delAuthor(Integer.parseInt(id));
        if (n>0){
            response.getWriter().write("{\"success\":\"success\"}");
        }else{
            response.getWriter().write("{\"error\":\"error\"}");
        }

    }


    //修改权限
    @RequestMapping("updateAuthor")
    public void UpdateAuthor(HttpServletRequest request,HttpServletResponse response,AuthorEntity authorEntity) throws IOException {
        request.setCharacterEncoding("utf-8");

        if (authorEntity.getIsParent()==false){
            authorEntity.setIsParent(1);
        }

        if (authorEntity.getIsParent()==true){
            authorEntity.setIsParent(0);
        }
        int n=userService.updateAuthor(authorEntity);
        if (n>0){
            response.getWriter().write("{\"success\":\"success\"}");
        }else{
            response.getWriter().write("{\"error\":\"error\"}");
        }

    }









    //查询下拉角色
    @RequestMapping("queryComboRoleAll")
    public void QueryRoleAll(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        List<RoleEntity> roleEntities=userService.queryComboRoleAll();
        response.getWriter().write(JSON.toJSONString(roleEntities));
    }

    @RequestMapping("")
    public void QueryRoleManage(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        String offset= request.getParameter("index");
        String limit= request.getParameter("size");
        String id=request.getParameter("id");


        Map<String,Object> parm=new HashMap<String,Object>();
        Map<String,Object> map=new HashMap<String,Object>();
        if (!StringUtils.isEmpty(id)){
            parm.put("id",id);
        }
        if (offset != null && limit != null) {
            parm.put("start", offset);
            parm.put("end", offset+limit);
        }






    }










}
