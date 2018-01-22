package com.DBUtile;


import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LimitAndSearchUtile {

     public static Map<String,Object> sumCondition(HttpServletRequest request) throws UnsupportedEncodingException {
         request.setCharacterEncoding("utf-8");
         Map<String,Object> map=new HashMap<String, Object>();
         String index=request.getParameter("index");
         String size=request.getParameter("size");
         String search=request.getParameter("search");

         if (!StringUtils.isNullOrEmpty(index)&&!StringUtils.isNullOrEmpty(size)){
             map.put("index", Integer.parseInt(index));
             map.put("size", Integer.parseInt(size)+Integer.parseInt(index));
         }
         if(!StringUtils.isNullOrEmpty(search)){
             search = URLDecoder.decode(search, "UTF-8");
             search=new String(search.getBytes("iso-8859-1"), "UTF-8");
             map.put("search",search);
         }
         return map;
     }
}
