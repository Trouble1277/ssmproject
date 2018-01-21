package com.DBUtile;


import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LimitAndSearchUtile {

     public static Map<String,Object> sumCondition(HttpServletRequest request){
         Map<String,Object> map=new HashMap<String, Object>();
         String index=request.getParameter("index");
         String size=request.getParameter("size");
         String search=request.getParameter("search");
         if (!StringUtils.isNullOrEmpty(index)&&!StringUtils.isNullOrEmpty(size)){
             map.put("index", Integer.parseInt(index));
             map.put("size", Integer.parseInt(size)+Integer.parseInt(index));
         }
         if(!StringUtils.isNullOrEmpty(search)){
             map.put("search",search);
         }
         return map;
     }
}
